package com.example.ch4_1_newsfeed.dto.user.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {
    @Size(max = 255, message = "이름은 255자 이하여야 합니다.")
    private String name;

    @Size(max = 255, message = "전체 url은 총 255자 이하여야 합니다.")
    private String pictureUrl;
}
