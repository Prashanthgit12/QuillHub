package com.service;

import com.entity.Blog;
import com.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // Get all blogs
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // Get blog by ID
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    // Create blog
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    // Update blog
    public Blog updateBlog(Long id, Blog updatedBlog) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(updatedBlog.getTitle());
            blog.setCategory(updatedBlog.getCategory());
            blog.setDescription(updatedBlog.getDescription());
            blog.setContent(updatedBlog.getContent());
            blog.setImage(updatedBlog.getImage());
            blog.setCreatedAt(updatedBlog.getCreatedAt());
            blog.setAuthorName(updatedBlog.getAuthorName());
            return blogRepository.save(blog);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    // Delete blog
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    // Get blogs by author
    public List<Blog> getBlogsByAuthor(String authorName) {
        return blogRepository.findByAuthorName(authorName);
    }

    // Get blogs by category
    public List<Blog> getBlogsByCategory(String category) {
        return blogRepository.findByCategory(category);
    }
}