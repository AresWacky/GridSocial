package com.gridsocial.service;

import com.gridsocial.model.Likes;
import com.gridsocial.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Autowired
    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public List<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

    public List<Likes> getLikesByUserId(Long userId) {
        return likesRepository.findByUserId(userId);
    }

    public List<Likes> getLikesByFeedId(Long feedId) {
        return likesRepository.findByFeedId(feedId);
    }

    public List<Likes> getLikesByCommentId(Long commentId) {
        return likesRepository.findByCommentId(commentId);
    }

    public Optional<Likes> getLikeById(Long id) {
        return likesRepository.findById(id);
    }

    public Likes saveLike(Likes like) {
        return likesRepository.save(like);
    }

    public void deleteLike(Long id) {
        likesRepository.deleteById(id);
    }
}
