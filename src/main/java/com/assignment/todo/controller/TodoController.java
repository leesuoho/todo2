package com.assignment.todo.controller;

import com.assignment.todo.dto.TodoCreateRequestDto;
import com.assignment.todo.dto.TodoCreateResponseDto;
import com.assignment.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodo(@RequestBody TodoCreateRequestDto requestDto) {
        TodoCreateResponseDto responseDto = todoService.createTodo(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
