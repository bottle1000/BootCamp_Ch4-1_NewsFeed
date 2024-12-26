package com.example.ch4_1_newsfeed.dto.feed.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FeedResponseDto {

    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
}