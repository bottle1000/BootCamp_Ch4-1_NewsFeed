package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.entity.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedRepositoryImpl {

    private final FeedRepository feedRepository;

    public Feed modifyFeed(Long userId, Long feedId) {
        return feedRepository.findByIdAndId(userId, feedId);
    }


}
