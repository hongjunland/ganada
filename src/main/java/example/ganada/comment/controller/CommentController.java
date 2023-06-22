package example.ganada.comment.controller;

import example.ganada.comment.dto.CreateCommentRequest;
import example.ganada.comment.dto.UpdateCommentRequest;
import example.ganada.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    final private CommentService commentService;
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest createCommentRequest){
        return ResponseEntity.ok(commentService.createComment(createCommentRequest));
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment( @PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest){
        return ResponseEntity.ok(commentService.updateComment(commentId, updateCommentRequest));
    }
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
