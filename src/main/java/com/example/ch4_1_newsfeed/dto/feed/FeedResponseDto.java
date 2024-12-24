package com.example.ch4_1_newsfeed.dto.feed;

import com.example.ch4_1_newsfeed.entity.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedResponseDto {

    private final Long id;
    private final String contents;
    private final LocalDateTime createdAt;

    public FeedResponseDto(Long id, String contents, LocalDateTime createdAt) {//, String imageUrl) {
        this.id = id;
        this.contents = contents;
        this.createdAt = createdAt;
    }

}



