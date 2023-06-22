package example.ganada.post.service;

import example.ganada.post.dto.CreatePostRequest;
import example.ganada.post.dto.CreatePostResponse;
import example.ganada.post.dto.UpdatePostRequest;
import example.ganada.member.entity.Member;
import example.ganada.post.entity.Post;
import example.ganada.post.mapper.PostMapper;
import example.ganada.member.service.MemberService;
import example.ganada.post.repository.PostRepository;
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
