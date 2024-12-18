package com.assignment.todo.controller;

import com.assignment.todo.dto.ApiResponse;
import com.assignment.todo.dto.TodoCreateRequestDto;
import com.assignment.todo.dto.TodoCreateResponseDto;
import com.assignment.todo.dto.TodoResponseDto;
import com.assignment.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<List<TodoResponseDto>>> getAllTodos() {
        List<TodoResponseDto> responseDtoList = todoService.getAllTodos();
        if (responseDtoList.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>("No todos found", responseDtoList));
        }
        return ResponseEntity.ok(new ApiResponse<>("Success", responseDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        TodoResponseDto responseDto = todoService.getTodoById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoCreateRequestDto requestDto) {
        if (!id.equals(requestDto.getId())) {
            throw new IllegalArgumentException("URL ID와 Body ID가 일치하지 않습니다.");
        }
        TodoResponseDto responseDto = todoService.updateTodo(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
