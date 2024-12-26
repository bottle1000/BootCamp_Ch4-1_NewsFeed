package com.example.ch4_1_newsfeed.dto.feed.response;

import com.example.ch4_1_newsfeed.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindByUserIdResponseDto {
    private Long userId;
    private String userName;
    private String description;
    private LocalDateTime createdAt;
    private List<Photo> photos;

}
