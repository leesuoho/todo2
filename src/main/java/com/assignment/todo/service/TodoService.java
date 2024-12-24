package com.assignment.todo.service;

import com.assignment.todo.dto.TodoCreateRequestDto;
import com.assignment.todo.dto.TodoCreateResponseDto;
import com.assignment.todo.dto.TodoResponseDto;
import com.assignment.todo.entity.Todo;
import com.assignment.todo.entity.User;
import com.assignment.todo.repository.TodoRepository;
import com.assignment.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TodoService 클래스는 Todo와 관련된 비즈니스 로직을 처리하는 서비스 클래스
 * Todo의 생성, 조회, 수정, 삭제 등을 담당
 */
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository; // Todo 엔티티 관련 데이터베이스 작업을 처리하는 리포지토리
    private final UserRepository userRepository; // User 엔티티 관련 데이터베이스 작업을 처리하는 리포지토리

    /**
     * 새로운 Todo를 생성하는 메서드
     *
     * @param requestDto Todo 생성 요청 데이터
     * @return 생성된 Todo에 대한 응답 데이터
     */
    @Transactional
    public TodoCreateResponseDto createTodo(TodoCreateRequestDto requestDto) {
        // 요청된 userId로 User 엔티티를 조회
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다: " + requestDto.getUserId()));

        // Todo 객체를 생성하고 값을 설정
        Todo todo = new Todo(requestDto.getTitle(), requestDto.getContents(), user);
//        todo.setTitle(requestDto.getTitle());
//        todo.setContents(requestDto.getContents());
//        todo.setUser(user); // User를 설정

        // 저장된 Todo를 반환
        Todo savedTodo = todoRepository.save(todo);
        return new TodoCreateResponseDto(savedTodo); // 장된 Todo를 DTO로 변환하여 반환
    }

    /**
     * 모든 Todo를 조회하는 메서드
     *
     * @return Todo 목록을 반환하는 응답 데이터
     */
    @Transactional
    public List<TodoResponseDto> getAllTodos() {
        // 모든 Todo 엔티티를 조회하고 DTO로 변환
        return todoRepository.findAll().stream()
                .map(TodoResponseDto::new) // Todo를 TodoResponseDto로 변환
                .collect(Collectors.toList()); // 리스트로 반환
    }

    /**
     * 특정 Todo를 ID로 조회하는 메서드
     *
     * @param id 조회할 Todo의 ID
     * @return 조회된 Todo에 대한 응답 데이터
     */
    @Transactional
    public TodoResponseDto getTodoById(Long id) {
        // Todo를 ID로 조회
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정을 찾을 수 없습니다: " + id));
        return new TodoResponseDto(todo); // 조회된 Todo를 DTO로 변환하여 반환
    }

    /**
     * Todo를 수정하는 메서드
     *
     * @param id         Todo의 ID
     * @param requestDto 수정할 Todo 데이터
     * @return 수정된 Todo에 대한 응답 데이터
     */
    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoCreateRequestDto requestDto) {
        // 수정할 Todo를 ID로 조회
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정을 찾을 수 없습니다: " + id));

        // Todo의 필드값을 수정
        todo.setTitle(requestDto.getTitle());
        todo.setContents(requestDto.getContents());

        // 수정된 Todo를 저장하고 반환
        Todo updatedTodo = todoRepository.save(todo);
        return new TodoResponseDto(updatedTodo); // 수정된 Todo를 DTO로 변환하여 반환
    }

    /**
     * Todo를 삭제하는 메서드
     *
     * @param id 삭제할 Todo의 ID
     */
    @Transactional
    public void deleteTodo(Long id) {
        // 삭제할 Todo를 ID로 조회
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 일정을 찾을 수 없습니다: " + id));
        todoRepository.delete(todo); // Todo 삭제
    }
}
