package com.blog.blogging_platform.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String text,
        LocalDateTime commentDate,
        Long postId,
        Long userId,
        String username
) {}