package com.example.ch4_1_newsfeed.dto.user.response;

import com.example.ch4_1_newsfeed.entity.Photo;
import com.example.ch4_1_newsfeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindByUserAndFeedIdResponseDto {
    private Long id;
    private String description;
    private User user;
    private List<Photo> photos;
    private LocalDateTime createdAt;

}