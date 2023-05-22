package example.ganada.service;

import example.ganada.dto.auth.LoginAuthRequest;
import example.ganada.dto.auth.MemberDetail;
import example.ganada.dto.auth.Token;
import example.ganada.dto.member.CreateMemberRequest;
import example.ganada.entity.Member;
import example.ganada.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService{
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authBuilder;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public Token login(LoginAuthRequest loginAuthRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginAuthRequest.getEmail(), loginAuthRequest.getPassword());
        Authentication auth = authBuilder.getObject().authenticate(authToken);
        Token token = tokenProvider.generateToken(auth);
        return token;
    }

    public Member signup(CreateMemberRequest memberRequest) {
        memberRequest.setPassword(encoder.encode(memberRequest.getPassword()));
        Member member = memberService.createMember(memberRequest);
        return member;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findByEmail(username);
        UserDetails user = new MemberDetail(member.getEmail(), member.getPassword(), null );
        return user;
    }
}
