package com.blog.blogging_platform.controller;

import com.blog.blogging_platform.Entities.Post;
import com.blog.blogging_platform.dto.PostRequest;
import com.blog.blogging_platform.dto.PostResponse;
import com.blog.blogging_platform.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest request) {
        Post post = new Post();
        post.setTitle(request.title());
        post.setContent(request.content());

        Post saved = postService.createPost(post, request.authorId());

        return toResponse(saved);
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return toResponse(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "Post deleted successfully";
    }

    private PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getPublishDate(),
                post.getAuthor().getId(),
                post.getAuthor().getUsername()
        );
    }
}
