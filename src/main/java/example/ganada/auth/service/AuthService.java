package example.ganada.auth.service;

import example.ganada.auth.dto.LoginAuthRequest;
import example.ganada.auth.dto.MemberDetail;
import example.ganada.auth.dto.Token;
import example.ganada.member.dto.CreateMemberRequest;
import example.ganada.member.entity.Member;
import example.ganada.member.service.MemberService;
import example.ganada.auth.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authBuilder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public Token login(LoginAuthRequest loginAuthRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginAuthRequest.getEmail(), loginAuthRequest.getPassword());
        Authentication auth = authBuilder.getObject().authenticate(authToken);
        Token token = tokenProvider.generateToken(auth);
        return token;
    }

    public Member signup(CreateMemberRequest memberRequest) {
        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword()));
        Member member = memberService.createMember(memberRequest);
        return member;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByEmail(username);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails user = new MemberDetail(member.getMemberId().toString(), member.getPassword(), authorities);
        return user;
    }

    public Long extractEmailFromToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("토큰정보가 유효하지 않습니다.");
        }
        return Long.parseLong(authentication.getName());
    }
}
