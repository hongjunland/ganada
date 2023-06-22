package example.ganada.post.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdatePostRequest extends PostRequest {
    private String title;
    private String content;
}
