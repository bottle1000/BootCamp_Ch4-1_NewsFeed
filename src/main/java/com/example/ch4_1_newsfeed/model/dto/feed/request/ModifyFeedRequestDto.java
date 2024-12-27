package com.example.ch4_1_newsfeed.model.dto.feed.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModifyFeedRequestDto {

    @Size(max = 255, message = "내용은 255자 이하여야 합니다.")
    private String contents;
}
