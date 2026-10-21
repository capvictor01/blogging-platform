package com.blog.blogging_platform.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    @Column(length = 5000)
    private String content;

    private LocalDateTime publishDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}