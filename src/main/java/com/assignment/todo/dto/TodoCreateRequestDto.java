package com.assignment.todo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * TodoCreateRequestDto는 할 일을 생성하거나 수정할 때 클라이언트로부터 전달받는 데이터를 담는 DTO
 */
@Getter
public class TodoCreateRequestDto {

    private Long id; // 할 일의 고유 ID
    private String title; // 할 일의 제목
    private String contents; // 할 일의 내용
    private Long userId; // 할 일을 작성한 사용자의 ID

}
