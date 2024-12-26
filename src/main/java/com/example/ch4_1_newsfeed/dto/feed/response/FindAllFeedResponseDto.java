package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindAllFeedResponseDto {
    private Long feed_id;
    private Long following_id;
    private String contents;
    private LocalDateTime createdAt;
    private List<Photo> photos;
}
