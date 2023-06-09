package example.ganada.auth.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    private String grantToken;
    private String accessToken;
    private String refreshToken;
    private Instant expiration;
}
