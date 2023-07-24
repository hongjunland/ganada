package example.ganada.auth.service;

import example.ganada.auth.dto.*;
import example.ganada.auth.repository.RefreshTokenRepository;
import example.ganada.member.dto.CreateMemberRequest;
import example.ganada.member.entity.Member;
import example.ganada.member.service.MemberService;
import example.ganada.auth.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authBuilder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.expiration.access}")
    private Long accessTokenExp;
    @Value("${jwt.expiration.refresh}")
    private Long refreshTokenExp;

    public Token login(LoginAuthRequest loginAuthRequest) {
        Member member = memberService.findByEmail(loginAuthRequest.getEmail());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginAuthRequest.getEmail(), loginAuthRequest.getPassword());
        Authentication auth = authBuilder.getObject().authenticate(authToken);
        Token token = tokenProvider.generateToken(auth);
        refreshTokenRepository.saveRefreshToken(member.getMemberId().toString(), token.getRefreshToken());
        refreshTokenRepository.expire(member.getMemberId().toString(), refreshTokenExp);
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

//    // Token -> member id
//    public Long extractMemberIdFromToken() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication);
//        if (authentication == null || authentication.getName() == null) {
//            throw new RuntimeException("토큰정보가 유효하지 않습니다.");
//        }
//        return Long.parseLong(authentication.getName());
//    }

    public RefreshTokenResponse reissue(Long memberId, RefreshTokenRequest refreshTokenRequest) {
        Member member = memberService.findById(memberId);
        String refreshToken = refreshTokenRepository.getRefreshToken(member.getMemberId().toString());
        if (!refreshTokenRequest.getRefreshToken().equals(refreshToken) || !tokenProvider.isValidToken(refreshToken)) {
            throw new RuntimeException("Refresh Token is not Valid");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getPassword(), authorities);
        String reIssuedAccessToken = tokenProvider.generateAccessToken(authentication);
        return RefreshTokenResponse.builder()
                .accessToken(reIssuedAccessToken)
                .expiration(Instant.now().plusMillis(accessTokenExp))
                .build();
    }

    public void logout(Long memberId) {
        Member member = memberService.findById(memberId);
        refreshTokenRepository.expire(member.getMemberId().toString(), 0L);
    }
}
