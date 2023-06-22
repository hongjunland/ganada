package example.ganada.comment.service;

import example.ganada.comment.dto.CreateCommentRequest;
import example.ganada.comment.dto.UpdateCommentRequest;
import example.ganada.comment.entity.Comment;
import example.ganada.post.entity.Post;
import example.ganada.comment.repository.CommentRepository;
import example.ganada.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public Comment findByCommentId(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return comment;
    }

    public Comment createComment(CreateCommentRequest createCommentRequest) {
        Post post = postService.findPostById(createCommentRequest.getPostId());
        Comment comment = Comment.builder()
                .content(createCommentRequest.getContent())
                .postId(post.getId())
                .build();
        return commentRepository.save(comment);
    }

    public List<Comment> findAllCommentsByPostId(Long postId) {
        Post post = postService.findPostById(postId);
        List<Comment> comments = commentRepository.findAllByPostId(post.getId());
        return comments;
    }

    public Comment updateComment(Long commentId, UpdateCommentRequest updateCommentRequest) {
        Comment comment = findByCommentId(commentId);
        return commentRepository.save(Comment.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .content(updateCommentRequest.getContent())
                .build());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
