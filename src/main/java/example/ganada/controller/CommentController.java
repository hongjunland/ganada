package example.ganada.controller;

import example.ganada.dto.comment.CreateCommentRequest;
import example.ganada.dto.comment.UpdateCommentRequest;
import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Comment;
import example.ganada.entity.Post;
import example.ganada.service.CommentService;
import example.ganada.service.PostService;
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
