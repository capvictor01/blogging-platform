package com.blog.blogging_platform.repository;

import com.blog.blogging_platform.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
