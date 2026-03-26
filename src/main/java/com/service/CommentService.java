package com.service;

import com.entity.Comment;
import com.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // GET all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // GET comments by blogId
    public List<Comment> getCommentsByBlogId(Long blogId) {
        return commentRepository.findByBlogId(blogId);
    }

    // ADD comment
    public Comment addComment(Comment comment) {
        comment.setCreatedAt(LocalDate.now()); // auto date
        return commentRepository.save(comment);
    }

    // DELETE comment
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}