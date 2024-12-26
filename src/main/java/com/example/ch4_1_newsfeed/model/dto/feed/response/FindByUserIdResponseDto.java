package com.example.ch4_1_newsfeed.model.dto.feed.response;

import com.example.ch4_1_newsfeed.model.entity.Feed;
import com.example.ch4_1_newsfeed.model.entity.Photo;
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

    public static FindByUserIdResponseDto from(Feed feed, List<Photo> photoList) {
        return new FindByUserIdResponseDto(
                feed.getUser().getId(),
                feed.getUser().getName(),
                feed.getContents(),
                feed.getCreatedAt(),
                photoList
        );
    }

}
