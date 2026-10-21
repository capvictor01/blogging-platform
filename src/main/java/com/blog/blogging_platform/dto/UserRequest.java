package com.blog.blogging_platform.dto;

public record UserRequest(
        String username,
        String email,
        String bio
) {}