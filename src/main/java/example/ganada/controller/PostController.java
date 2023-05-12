package example.ganada.controller;

import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Post;
import example.ganada.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    final private PostService postService;
    @GetMapping
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.ok(postService.findAllPost());
    }
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest){
        Post post = postService.createPost(createPostRequest);
        return ResponseEntity.ok(post);
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
}
