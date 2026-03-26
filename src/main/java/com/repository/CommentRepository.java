package com.repository;

import com.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 🔥 Get comments by blogId
    List<Comment> findByBlogId(Long blogId);
}