package com.blog.blogging_platform.dto;


public record PostRequest(
        String title,
        String content,
        Long authorId
) {}