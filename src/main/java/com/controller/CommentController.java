package com.controller;

import com.entity.Comment;
import com.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // GET all comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // GET comments by blogId
    @GetMapping("/blog/{blogId}")
    public List<Comment> getByBlogId(@PathVariable Long blogId) {
        return commentService.getCommentsByBlogId(blogId);
    }

    // ADD comment
    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    // DELETE comment
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}