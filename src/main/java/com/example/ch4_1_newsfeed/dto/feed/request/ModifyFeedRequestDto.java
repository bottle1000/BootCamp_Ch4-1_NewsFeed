package com.example.ch4_1_newsfeed.dto.feed.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModifyFeedRequestDto {
    @NotNull(message = "id가 포함되어야 합니다")
    @Positive(message = "id는 양의 정수여야 합니다")
    private Long feed_id;

    @Size(max = 255, message = "내용은 255자 이하여야 합니다.")
    private String contents;
}
