
package com.blog.blogging_platform.service;

import com.blog.blogging_platform.Entities.Comment;
import com.blog.blogging_platform.Entities.Post;
import com.blog.blogging_platform.Entities.User;
import com.blog.blogging_platform.repository.CommentRepository;
import com.blog.blogging_platform.repository.PostRepository;
import com.blog.blogging_platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // Add a comment to a post
    public Comment addComment(
            Comment comment,
            Long postId,
            Long userId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Post not found: " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found: " + userId));

        comment.setPost(post);
        comment.setUser(user);
        comment.setCommentDate(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    // Get all comments belonging to a specific post
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // Delete a comment by ID
    public void deleteComment(Long id) {

        if (!commentRepository.existsById(id)) {
            throw new RuntimeException(
                    "Comment not found: " + id);
        }

        commentRepository.deleteById(id);
    }
}
