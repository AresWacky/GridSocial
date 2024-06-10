package com.gridsocial.repository;

import com.gridsocial.model.Comment;
import com.gridsocial.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByFeedId(Long feedId);
}