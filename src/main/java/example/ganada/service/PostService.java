package example.ganada.service;

import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Post;
import example.ganada.mapper.PostMapper;
import example.ganada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시물이 존재하지 않습니다."));
        return post;
    }

    public Post createPost(Long memberId, CreatePostRequest createPostRequest) {
        Post post = PostMapper.INSTANCE.toEntity(createPostRequest);
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
        Post post = findPostById(postId);
        PostMapper.INSTANCE.updatePostFromDto(post, updatePostRequest);
        return postRepository.save(post);
    }
}
