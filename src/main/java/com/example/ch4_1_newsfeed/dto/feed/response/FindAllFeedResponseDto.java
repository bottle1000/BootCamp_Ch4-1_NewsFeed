package com.example.ch4_1_newsfeed.dto.feed.response;

import jakarta.persistence.SqlResultSetMapping;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    private String photos;
}
