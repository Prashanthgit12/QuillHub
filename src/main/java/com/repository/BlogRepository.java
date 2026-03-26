package com.repository;

import com.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    // Get blogs by author
    List<Blog> findByAuthorName(String authorName);

    // Get blogs by category
    List<Blog> findByCategory(String category);
}