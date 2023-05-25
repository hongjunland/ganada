package example.ganada.dto.post;

import example.ganada.entity.Member;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreatePostRequest extends PostRequest {
    private String title;
    private String content;
}
