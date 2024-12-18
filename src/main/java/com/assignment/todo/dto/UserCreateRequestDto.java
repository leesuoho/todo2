package com.assignment.todo.dto;

import lombok.Getter;

/**
 * UserCreateRequestDto는 사용자 생성 요청 시 클라이언트로부터 전달받는 데이터를 담는 DTO
 */
@Getter
public class UserCreateRequestDto {

    private String username;
    private String email;
    private String password;
}
