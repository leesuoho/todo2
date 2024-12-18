package com.assignment.todo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * LoginRequestDto는 로그인 요청에 필요한 데이터를 담는 DTO
 * 클라이언트가 로그인할 때 이메일과 비밀번호를 서버에 전달하는데 사용
 */
@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
