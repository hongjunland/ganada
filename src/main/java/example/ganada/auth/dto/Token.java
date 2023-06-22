package example.ganada.auth.dto;


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
