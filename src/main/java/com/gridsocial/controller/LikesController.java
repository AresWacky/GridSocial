package com.gridsocial.controller;

import com.gridsocial.model.Likes;
import com.gridsocial.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesRepository likeRepository;

    @GetMapping
    public List<Likes> getAllLikes() {
        return likeRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Likes> getLikesByUser(@PathVariable Long userId) {
        return likeRepository.findByUserId(userId);
    }

    @GetMapping("/post/{postId}")
    public List<Likes> getLikesByPost(@PathVariable Long postId) {
        return likeRepository.findByPostId(postId);
    }

    @GetMapping("/comment/{commentId}")
    public List<Likes> getLikesByComment(@PathVariable Long commentId) {
        return likeRepository.findByCommentId(commentId);
    }

    @PostMapping
    public Likes createLike(@RequestBody Likes like) {
        like.setCreatedAt(LocalDateTime.now());
        return likeRepository.save(like);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable Long id) {
        return likeRepository.findById(id)
                .map(record -> {
                    likeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
