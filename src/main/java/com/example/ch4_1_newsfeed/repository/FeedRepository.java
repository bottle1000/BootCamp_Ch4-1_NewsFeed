package com.example.ch4_1_newsfeed.repository;

import com.example.ch4_1_newsfeed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    /**
     * 유저 ID 기반으로 피드 데이터를 가져옴
     */
    List<Feed> findAllByUserId(Long userId);
}
