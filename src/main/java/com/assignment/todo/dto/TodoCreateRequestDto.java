package com.assignment.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TodoCreateRequestDto {

    @Setter
    private Long id;

    private String title;
    private String contents;
    private Long userId;

}
