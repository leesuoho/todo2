package com.assignment.todo.service;

import com.assignment.todo.dto.TodoCreateRequestDto;
import com.assignment.todo.dto.TodoCreateResponseDto;
import com.assignment.todo.dto.TodoResponseDto;
import com.assignment.todo.entity.Todo;
import com.assignment.todo.entity.User;
import com.assignment.todo.repository.TodoRepository;
import com.assignment.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TodoResponseDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정을 찾을 수 없습니다: " + id));
        return new TodoResponseDto(todo);
    }

    public TodoResponseDto updateTodo(Long id, TodoCreateRequestDto requestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정을 찾을 수 없습니다: " + id));

        todo.setTitle(requestDto.getTitle());
        todo.setContents(requestDto.getContents());

        Todo updatedTodo = todoRepository.save(todo);
        return new TodoResponseDto(updatedTodo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정을 찾을 수 없습니다: " + id));
        todoRepository.delete(todo);
    }
}
