package example.ganada.controller;

import example.ganada.dto.auth.LoginAuthRequest;
import example.ganada.dto.auth.LoginAuthRequest;
import example.ganada.dto.member.CreateMemberRequest;
import example.ganada.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginAuthRequest loginAuthRequest){
        return ResponseEntity.ok(authService.login(loginAuthRequest));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CreateMemberRequest memberRequest){
        return ResponseEntity.ok(authService.signup(memberRequest));
    }
}
