package com.gridsocial.service;

import com.gridsocial.model.Feed;
import com.gridsocial.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedService {

    private final FeedRepository feedRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public List<Feed> getAllFeeds() {
        return feedRepository.findAll();
    }

    public Optional<Feed> getFeedById(Long id) {
        return feedRepository.findById(id);
    }

    public List<Feed> getFeedsByUserId(Long userId) {
        return feedRepository.findByUserId(userId);
    }

    public Feed saveFeed(Feed feed) {
        return feedRepository.save(feed);
    }

    public void deleteFeed(Long id) {
        feedRepository.deleteById(id);
    }
}
