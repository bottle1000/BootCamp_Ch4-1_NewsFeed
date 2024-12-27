package com.example.ch4_1_newsfeed.model.dto.feed.response;

import com.example.ch4_1_newsfeed.model.entity.Feed;
import com.example.ch4_1_newsfeed.model.entity.Photo;
import com.example.ch4_1_newsfeed.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindByUserAndFeedIdResponseDto {
    private Long id;
    private String contents;
    private User user;
    private List<Photo> photos;
    private LocalDateTime createdAt;

    public static FindByUserAndFeedIdResponseDto from(Feed feed, List<Photo> photoList) {
        return new FindByUserAndFeedIdResponseDto(
                feed.getId(),
                feed.getContents(),
                feed.getUser(),
                photoList,
                feed.getCreatedAt()
        );
    }
}
