package example.ganada.dto.auth;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    private String grantToken;
    private String accessToken;
    private String refreshToken;
}
