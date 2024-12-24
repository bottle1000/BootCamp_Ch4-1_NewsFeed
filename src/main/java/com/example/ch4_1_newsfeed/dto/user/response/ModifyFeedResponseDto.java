package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.Photo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ModifyFeedResponseDto {

    private final Long id;
    private final String contents;
    private final List<Photo> photos;
    private final LocalDateTime createdAt;

    public ModifyFeedResponseDto(Long id, String contents, List<Photo> photos, LocalDateTime createdAt) {
        this.id = id;
        this.contents = contents;
        this.photos = photos;
        this.createdAt = createdAt;
    }
}
