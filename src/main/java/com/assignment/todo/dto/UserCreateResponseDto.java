package com.assignment.todo.dto;

import com.assignment.todo.entity.User;
import lombok.Getter;

@Getter
public class UserCreateResponseDto {

    private Long id;
    private String username;
    private String email;

    public UserCreateResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
