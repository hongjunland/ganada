package example.ganada.member.dto;

import example.ganada.common.BaseResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@Getter
@Setter
@SuperBuilder
public class MemberResponse extends BaseResponseDto {
    private Long memberId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
}
