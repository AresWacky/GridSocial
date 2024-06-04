package com.gridsocial.service;

import com.gridsocial.model.Comment;
import com.gridsocial.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with ID " + id + " not found"));
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByFeedId(long feedId) {
        return commentRepository.findByFeedId(feedId);
    }
}