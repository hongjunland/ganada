package example.ganada.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateCommentRequest {
    private String content;
    private Long postId;
}
