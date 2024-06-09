package com.gridsocial.controller;

import com.gridsocial.model.Feed;
import com.gridsocial.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {

    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public List<Feed> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feed> getFeedById(@PathVariable Long id) {
        Optional<Feed> feed = feedService.getFeedById(id);
        return feed.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Feed> getFeedsByUserId(@PathVariable Long userId) {
        return feedService.getFeedsByUserId(userId);
    }

    @PostMapping
    public Feed createFeed(@RequestBody Feed feed) {
        feed.setCreatedAt(LocalDateTime.now());
        return feedService.saveFeed(feed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long id) {
        return feedService.getFeedById(id)
                .map(record -> {
                    feedService.deleteFeed(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
