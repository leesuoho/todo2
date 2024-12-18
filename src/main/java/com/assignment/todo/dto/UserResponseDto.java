package com.assignment.todo.dto;

import com.assignment.todo.entity.User;
import lombok.Getter;

/**
 * UserResponseDto는 사용자 정보를 클라이언트에게 응답으로 전달하기 위한 DTO
 */
@Getter
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String createdAt;

    /**
     * 생성자: User 엔티티 객체를 받아 해당 엔티티 데이터를 DTO로 변환
     *
     * @param user 클라이언트에 전달할 사용자 정보를 담고 있는 User 엔티티
     */
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt().toString();
    }
}
