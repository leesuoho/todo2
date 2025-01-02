package com.assignment.todo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * UserCreateRequestDto는 사용자 생성 요청 시 클라이언트로부터 전달받는 데이터를 담는 DTO
 */
@Getter
public class UserCreateRequestDto {

    @NotBlank
    @Size(max = 15, message = "이름은 15자 이하여야 합니다.")
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 16, message = "비밀번호는 4자이상 16자 이하여야 합니다.")
    private String password;
}
