package com.assignment.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ApiResponse는 API 응답 데이터를 감싸는 DTO
 *
 * @param <T> 응답 데이터의 타입을 제네릭으로 지정하여, 다양한 타입의 데이터를 처리할 수 있다
 */
@Getter
@AllArgsConstructor // 모든 필드를 초기화하는 생성자를 자동으로 생성
public class ApiResponse<T> {
    private String message; // API 응답 메시지
    private T data; // 응답 데이터 (응답에 따라 다르게 전달되는 데이터)
}
