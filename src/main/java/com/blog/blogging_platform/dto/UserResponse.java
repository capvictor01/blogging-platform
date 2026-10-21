package com.blog.blogging_platform.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        String bio
) {}