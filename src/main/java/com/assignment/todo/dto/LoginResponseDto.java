package com.assignment.todo.dto;

import lombok.Getter;

/**
 * LoginResponseDto는 로그인 요청에 대한 응답 데이터를 담는 DTO
 * 로그인 성공 후 사용자 정보를 응답으로 클라이언트에게 전달
 */
@Getter
public class LoginResponseDto {
    private Long id;
    private String username;

    /**
     * 생성자: 로그인 성공 후 응답을 반환하기 위해 사용자 정보를 초기화
     *
     * @param id       사용자 ID
     * @param username 사용자 이름
     */
    public LoginResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
