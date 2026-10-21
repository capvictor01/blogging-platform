package com.blog.blogging_platform.repository;

import com.blog.blogging_platform.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
