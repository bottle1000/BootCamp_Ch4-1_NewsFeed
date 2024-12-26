package com.example.ch4_1_newsfeed.dto.feed.request;

import lombok.Getter;

@Getter
public class ModifyFeedRequestDto {

    /**
     * userId, feedId 데이터 추가로 받아야함
     */
    private Long feedId;
    private String description;
}
