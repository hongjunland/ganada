package example.ganada.service;

import example.ganada.dto.post.CreatePostRequest;
import example.ganada.dto.post.CreatePostResponse;
import example.ganada.dto.post.UpdatePostRequest;
import example.ganada.entity.Member;
import example.ganada.entity.Post;
import example.ganada.mapper.PostMapper;
import example.ganada.repository.MemberRepository;
import example.ganada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;

    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 게시물이 존재하지 않습니다."));
        return post;
    }

    public CreatePostResponse createPost(Long memberId, CreatePostRequest createPostRequest) {
        Member member = memberService.findById(memberId);
        Post post = Post.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .member(member)
                .build();
//        Post post = PostMapper.INSTANCE.toEntity(member, createPostRequest);
        Post newPost = postRepository.save(post);
        CreatePostResponse createPostResponse = CreatePostResponse.builder()
                .id(newPost.getId())
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .createdAt(newPost.getCreatedAt())
                .updatedAt(newPost.getUpdatedAt())
                .nickname(newPost.getMember().getNickname())
                .build();
        return createPostResponse;
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
