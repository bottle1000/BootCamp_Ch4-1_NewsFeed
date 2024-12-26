package com.example.ch4_1_newsfeed.dto.feed;

import com.example.ch4_1_newsfeed.entity.Feed;
import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FeedResponseDto {

    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
}