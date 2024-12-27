package com.example.ch4_1_newsfeed.model.dto.feed.response;

import com.example.ch4_1_newsfeed.model.entity.Feed;
import com.example.ch4_1_newsfeed.model.entity.User;
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

    //정적 팩토리 메서드
    public static FeedResponseDto from(Feed feed) {
        return new FeedResponseDto(
                feed.getId(),
                feed.getUser().getName(),
                feed.getContents(),
                feed.getCreatedAt()
        );
    }
}