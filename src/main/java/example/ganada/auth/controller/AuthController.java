package example.ganada.auth.controller;

import example.ganada.auth.dto.LoginAuthRequest;
import example.ganada.auth.dto.RefreshTokenDto;
import example.ganada.auth.dto.RefreshTokenRequest;
import example.ganada.common.BaseResponse;
import example.ganada.member.dto.CreateMemberRequest;
import example.ganada.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAuthRequest loginAuthRequest) {
        return ResponseEntity.ok(authService.login(loginAuthRequest));
    }
    @PostMapping("/logout")
    public void logout(){
        Long memberId = authService.extractMemberIdFromToken();
        authService.logout(memberId);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CreateMemberRequest memberRequest) {
        return ResponseEntity.ok(authService.signup(memberRequest));
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        Long memberId = authService.extractMemberIdFromToken();
        return ResponseEntity.ok(authService.reissue(memberId, refreshTokenRequest));
    }
}
