package com.assignment.todo.controller;

import com.assignment.todo.dto.TodoCreateRequestDto;
import com.assignment.todo.dto.TodoCreateResponseDto;
import com.assignment.todo.dto.TodoResponseDto;
import com.assignment.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        List<TodoResponseDto> responseDtoList = todoService.getAllTodos();
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        TodoResponseDto responseDto = todoService.getTodoById(id);
        return ResponseEntity.ok(responseDto);
    }
}
