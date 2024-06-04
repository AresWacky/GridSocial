package com.gridsocial.service;

import com.gridsocial.model.Feed;
import com.gridsocial.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;

    public List<Feed> getAllFeeds() {
        return feedRepository.findAll();
    }

    public Feed getFeedById(long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feed with ID " + id + " not found"));
    }

    public void deleteFeed(long id) {
        feedRepository.deleteById(id);
    }

    public void saveFeed(Feed feed) {
        feedRepository.save(feed);
    }

    public void updateFeed(Feed feed) {
        feedRepository.save(feed);
    }
}