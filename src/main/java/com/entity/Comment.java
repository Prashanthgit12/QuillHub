package com.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String comment;
    private String rating;

    private Long blogId; // 🔥 IMPORTANT (match with Blog ID)

    private LocalDate createdAt;

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }

    public Long getBlogId() { return blogId; }

    public void setBlogId(Long blogId) { this.blogId = blogId; }

    public LocalDate getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}