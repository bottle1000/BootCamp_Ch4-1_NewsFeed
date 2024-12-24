package com.example.ch4_1_newsfeed.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {
    @NotBlank(message = "이름이 포함되어야 합니다.")
    @Size(max = 255, message = "이름은 255자 이하여야 합니다.")
    private String name;
    // 요청에서 바로 이미지 경로를 받을 것인지 이미지 파일을 받아 저장경로를 따로 지정할 것인지 논의 필요
    private String pictureUrl;
}
