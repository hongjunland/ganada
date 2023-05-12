package example.ganada.dto.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UpdatePostRequest {
    private String title;
    private String content;
}
