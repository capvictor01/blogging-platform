package com.blog.blogging_platform.controller;

import com.blog.blogging_platform.Entities.Comment;
import com.blog.blogging_platform.dto.CommentRequest;
import com.blog.blogging_platform.dto.CommentResponse;
import com.blog.blogging_platform.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponse addComment(
            @RequestBody CommentRequest request) {

        Comment comment = new Comment();
        comment.setText(request.text());

        Comment saved = commentService.addComment(
                comment,
                request.postId(),
                request.userId()
        );

        return toResponse(saved);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponse> getCommentsByPost(
            @PathVariable Long postId) {

        return commentService.getCommentsByPost(postId).stream()
                .map(this::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "Comment deleted successfully";
    }

    private CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getCommentDate(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername()
        );
    }
}
