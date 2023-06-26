package example.ganada.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class RefreshTokenRequest {
    private String refreshToken;
}
