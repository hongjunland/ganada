package example.ganada.post.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreatePostRequest extends PostRequest {
    private String title;
    private String content;
}
