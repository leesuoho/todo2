package com.assignment.todo.dto;

import com.assignment.todo.entity.Todo;
import lombok.Getter;

/**
 * TodoResponseDto는 특정 할 일 정보를 클라이언트에게 응답으로 전달하기 위한 DTO
 */
@Getter
public class TodoResponseDto {

    private Long id; // 할 일의 고유 ID
    private String username; // 할 일을 작성한 사용자의 사용자 이름
    private String title; // 할 일의 제목
    private String contents; // 할 일의 내용
    private String createdAt; // 할 일이 생성된 날짜 및 시간
    private String modifiedAt; // 할 일이 마지막으로 수정된 날짜 및 시간

    /**
     * 생성자: Todo 엔티티 객체를 받아 해당 엔티티의 데이터를 DTO로 변환
     *
     * @param todo 클라이언트에 전달할 할 일을 나타내는 Todo 엔티티
     */
    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.username = todo.getUser().getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createdAt = todo.getCreatedAt().toString();
        this.modifiedAt = todo.getModifiedAt().toString();
    }
}
