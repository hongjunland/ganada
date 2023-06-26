package example.ganada.auth.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponse {
    private String accessToken;
    private Instant expiration;
}
