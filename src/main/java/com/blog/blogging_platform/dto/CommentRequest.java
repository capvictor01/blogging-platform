package com.blog.blogging_platform.dto;

public record CommentRequest(
        String text,
        Long postId,
        Long userId
) {}