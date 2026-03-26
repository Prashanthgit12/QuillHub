package com.controller;

import com.entity.Blog;
import com.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@CrossOrigin(origins = "http://localhost:5173")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // ✅ GET all blogs
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // ✅ GET blog by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ CREATE blog
    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    // ✅ UPDATE blog
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        try {
            return ResponseEntity.ok(blogService.updateBlog(id, blog));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ DELETE blog
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    // ✅ GET blogs by author
    @GetMapping("/author/{authorName}")
    public List<Blog> getBlogsByAuthor(@PathVariable String authorName) {
        return blogService.getBlogsByAuthor(authorName);
    }

    // ✅ GET blogs by category
    @GetMapping("/category/{category}")
    public List<Blog> getBlogsByCategory(@PathVariable String category) {
        return blogService.getBlogsByCategory(category);
    }
}