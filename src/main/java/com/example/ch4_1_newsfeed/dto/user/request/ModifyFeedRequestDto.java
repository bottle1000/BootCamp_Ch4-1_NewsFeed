package com.example.ch4_1_newsfeed.dto.user.request;

import com.example.ch4_1_newsfeed.entity.Photo;
import lombok.Getter;

import java.util.List;

@Getter
public class ModifyFeedRequestDto {

    /**
     * userId, feedId 데이터 추가로 받아야함
     */
    private Long userId;
    private Long feedId;
    private String description;
    private List<Photo> photos;
}
