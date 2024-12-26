package com.example.ch4_1_newsfeed.dto.feed.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedRequestDto {
    @Size(max = 255, message = "내용은 255자 이하여야 합니다.")
    private String contents;

    @Size(max = 255, message = "이름은 255자 이하여야 합니다.")
    private String name;
}


