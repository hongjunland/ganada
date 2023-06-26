package example.ganada.auth.dto;


import example.ganada.member.entity.Member;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenDto {
    private Member member;
    private String refreshToken;
    private Instant expireDate;
}
