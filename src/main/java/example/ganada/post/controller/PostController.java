package example.ganada.post.controller;

import example.ganada.post.dto.CreatePostRequest;
import example.ganada.post.dto.CreatePostResponse;
import example.ganada.post.dto.UpdatePostRequest;
import example.ganada.auth.service.AuthService;
import example.ganada.comment.service.CommentService;
import example.ganada.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    final private PostService postService;
    final private CommentService commentService;
    final private AuthService authService;
    @GetMapping
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.ok(postService.findAllPost());
    }
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest){
        Long memberId = authService.extractEmailFromToken();
        CreatePostResponse response = postService.createPost(memberId, createPostRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId){
        return ResponseEntity.ok(postService.findPostById(postId));
    }
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest){
        return ResponseEntity.ok(postService.updatePostRequest(postId, updatePostRequest));
    }
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.findAllCommentsByPostId(postId));
    }
}
