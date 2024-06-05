package com.csc340.test.adim.feed;


import com.csc340.test.adim.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}