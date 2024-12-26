package com.example.ch4_1_newsfeed.dto.feed.response;

import com.example.ch4_1_newsfeed.entity.Photo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ModifyFeedResponseDto {

    private final Long id;
    private final String description;
    private final List<Photo> photos;
    private final LocalDateTime createdAt;

    public ModifyFeedResponseDto(Long id, String description, List<Photo> photos, LocalDateTime createdAt) {
        this.id = id;
        this.description = description;
        this.photos = photos;
        this.createdAt = createdAt;
    }
}
