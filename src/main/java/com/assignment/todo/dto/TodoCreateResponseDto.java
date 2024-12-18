package com.assignment.todo.dto;

import com.assignment.todo.entity.Todo;
import lombok.Getter;

/**
 * TodoCreateResponseDto는 할 일을 생성한 후 클라이언트에게 응답으로 전달하는 데이터를 담는 DTO
 */
@Getter
public class TodoCreateResponseDto {

    private Long id;
    private String title;
    private String contents;

    /**
     * 생성자: Todo 엔티티 객체를 받아서 응답 DTO를 초기화
     *
     * @param todo 생성된 할 일을 나타내는 Todo 엔티티
     */
    public TodoCreateResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
    }
}
