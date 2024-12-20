package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
