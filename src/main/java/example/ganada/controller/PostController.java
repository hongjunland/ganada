package example.ganada.controller;

import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.CreatePostResponse;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Member;
import example.ganada.entity.Post;
import example.ganada.service.AuthService;
import example.ganada.service.CommentService;
import example.ganada.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
