package com.blog.blogging_platform.service;


import com.blog.blogging_platform.Entities.Post;
import com.blog.blogging_platform.Entities.User;
import com.blog.blogging_platform.repository.PostRepository;
import com.blog.blogging_platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(Post post, Long authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() ->
                        new RuntimeException("Author not found: " + authorId));

        post.setAuthor(author);
        post.setPublishDate(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Post not found: " + id));
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found: " + id);
        }
        postRepository.deleteById(id);
    }
}