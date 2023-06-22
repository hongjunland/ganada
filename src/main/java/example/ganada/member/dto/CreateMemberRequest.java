package example.ganada.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateMemberRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String nickname;
}
