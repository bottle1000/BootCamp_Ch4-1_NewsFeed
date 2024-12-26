package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.entity.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FeedRepositoryImpl {

    private final FeedRepository feedRepository;

    public Optional<Feed> findByFeedId(Long feedId) {
        return feedRepository.findById(feedId);
    }


}
