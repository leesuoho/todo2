package com.assignment.todo.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequestDto {

    private String title;
    private String contents;
    private Long userId;
}
