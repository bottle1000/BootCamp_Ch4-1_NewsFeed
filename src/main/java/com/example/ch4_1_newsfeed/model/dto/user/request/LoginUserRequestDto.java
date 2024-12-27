package com.example.ch4_1_newsfeed.model.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserRequestDto {
    @NotBlank(message = "이메일이 포함되어야 합니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호가 포함되어야 합니다.")
    @Size(max = 255, message = "비밀번호는 255 이하여야 합니다.")
    private String password;
}
