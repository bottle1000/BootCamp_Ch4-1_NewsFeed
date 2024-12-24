package com.example.ch4_1_newsfeed.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {
    @NotBlank(message = "비밀번호가 포함되어야 합니다.")
    @Size(max = 255, message = "비밀번호는 255자 내로 작성해주세요.")
    private String password;
}
