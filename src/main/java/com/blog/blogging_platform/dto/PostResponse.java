package com.blog.blogging_platform.dto;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        LocalDateTime publishDate,
        Long authorId,
        String authorUsername
) {}