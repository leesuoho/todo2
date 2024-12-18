package com.assignment.todo.dto;

import com.assignment.todo.entity.User;
import lombok.Getter;

/**
 * UserCreateResponseDto는 사용자 생성 요청에 대한 응답 데이터를 담는 DTO
 * 사용자 생성 후 클라이언트에게 반환될 사용자 정보를 제공
 */
@Getter
public class UserCreateResponseDto {

    private Long id;
    private String username;
    private String email;

    /**
     * 생성자: User 엔티티 객체를 받아 응답 DTO를 초기화
     *
     * @param user 생성된 사용자의 정보를 담고 있는 User 엔티티
     */
    public UserCreateResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
