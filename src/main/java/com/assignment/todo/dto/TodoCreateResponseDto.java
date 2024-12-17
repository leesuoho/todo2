package com.assignment.todo.dto;

import com.assignment.todo.entity.Todo;
import lombok.Getter;

@Getter
public class TodoCreateResponseDto {

    private Long id;
    private String title;
    private String contents;

    public TodoCreateResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
    }
}
