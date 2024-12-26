package com.example.ch4_1_newsfeed.model.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePasswordUserRequestDto {
    @NotBlank(message = "비밀번호가 포함되어야 합니다.")
    @Size(max = 255, message = "비밀번호는 255 이하여야 합니다.")
    private String password;
}
