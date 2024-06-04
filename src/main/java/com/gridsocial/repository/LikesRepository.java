package com.gridsocial.repository;

import com.gridsocial.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByUserId(Long userId);
    List<Likes> findByFeedId(Long feedId);
    List<Likes> findByCommentId(Long commentId);
}
