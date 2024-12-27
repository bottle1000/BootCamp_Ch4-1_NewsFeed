package com.example.ch4_1_newsfeed.model.dto.feed.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FeedPagingRequestDto {
    @NotNull(message = "page가 포함되어야 합니다.")
    @PositiveOrZero(message = "page는 양의 정수 또는 0여어야 합니다.")
    private final Integer page;

    @Positive(message = "size는 양의 정수여야 합니다.")
    private final Integer size;

    public FeedPagingRequestDto(Integer page, Integer size) {
        this.page = page;
        this.size = (size == null) ? 10 : size;
    }
}
