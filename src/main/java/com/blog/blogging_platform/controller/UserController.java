package com.blog.blogging_platform.controller;

import com.blog.blogging_platform.Entities.User;
import com.blog.blogging_platform.dto.UserRequest;
import com.blog.blogging_platform.dto.UserResponse;
import com.blog.blogging_platform.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setBio(request.bio());

        User saved = userService.createUser(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getBio()
        );
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getBio()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
