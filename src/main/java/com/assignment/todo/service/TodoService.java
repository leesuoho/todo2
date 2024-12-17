package com.assignment.todo.service;

import com.assignment.todo.dto.TodoCreateRequestDto;
import com.assignment.todo.dto.TodoCreateResponseDto;
import com.assignment.todo.entity.Todo;
import com.assignment.todo.entity.User;
import com.assignment.todo.repository.TodoRepository;
import com.assignment.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoCreateResponseDto createTodo(TodoCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다: " + requestDto.getUserId()));

        Todo todo = new Todo();
        todo.setTitle(requestDto.getTitle());
        todo.setContents(requestDto.getContents());
        todo.setUser(user);

        Todo savedTodo = todoRepository.save(todo);

        return new TodoCreateResponseDto(savedTodo);
    }
}
