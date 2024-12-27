package com.example.ch4_1_newsfeed.model.dto.feed.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindAllFeedResponseDto {
    private Long feedId;
    private Long followingId;
    private String contents;
    private LocalDateTime createdAt;
    @Setter
    private List<String> photos;

    public FindAllFeedResponseDto(Long feedId, Long followingId, String contents, LocalDateTime createdAt) {
        this.feedId = feedId;
        this.followingId = followingId;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
