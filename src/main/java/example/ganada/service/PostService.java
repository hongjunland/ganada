package example.ganada.service;

import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Comment;
import example.ganada.entity.Post;
import example.ganada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return post;
    }

    public Post createPost(CreatePostRequest createPostRequest) {
        Post post = Post.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .build();
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> findAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    public Post updatePostRequest(Long postId, UpdatePostRequest updatePostRequest) {
        postRepository.findById(postId).orElseThrow(() -> new RuntimeException());
        Post newPost = Post.builder()
                .id(postId)
                .title(updatePostRequest.getTitle())
                .content(updatePostRequest.getContent())
                .build();
        return postRepository.save(newPost);

    }
}
