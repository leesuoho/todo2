package com.assignment.todo.dto;

import com.assignment.todo.entity.Todo;
import lombok.Getter;

@Getter
public class TodoResponseDto {

    private Long id;
    private String username;
    private String title;
    private String contents;
    private String createdAt;
    private String modifiedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.username = todo.getUser().getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt().toString();
        this.modifiedAt = todo.getModifiedAt().toString();
    }
}
