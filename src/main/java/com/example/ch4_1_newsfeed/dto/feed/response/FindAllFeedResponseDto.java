package com.example.ch4_1_newsfeed.dto.feed.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FindAllFeedResponseDto {
    private Long feed_id;
    private Long following_id;
    private String contents;
    private LocalDateTime createdAt;
    private String photos;
}
