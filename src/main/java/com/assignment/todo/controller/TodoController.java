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

/**
 * TodoController는 Todo 관련 요청을 처리하는 REST API 컨트롤러입니다.
 * 클라이언트로부터 요청을 받아서 TodoService를 통해 작업을 처리합니다.
 */
@RestController
@RequestMapping("/todos") // 모든 요청은 "/todos" 경로로 시작합니다.
@RequiredArgsConstructor // final로 선언된 의존성을 자동으로 주입해주는 Lombok 어노테이션
public class TodoController {

    private final TodoService todoService; // Todo 비즈니스 로직을 처리하는 서비스 클래스

    /**
     * 새로운 Todo를 생성합니다.
     *
     * @param requestDto Todo 생성 요청 데이터를 담은 객체
     * @return 생성된 Todo의 응답 데이터를 포함하는 ResponseEntity
     */
    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodo(@RequestBody TodoCreateRequestDto requestDto) {
        TodoCreateResponseDto responseDto = todoService.createTodo(requestDto);
        return ResponseEntity.ok(responseDto); // HTTP 상태 코드 200과 함께 응답 데이터를 반환
    }

    /**
     * 모든 Todo를 조회합니다.
     *
     * @return 모든 Todo 목록을 포함한 ApiResponse 객체
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponseDto>>> getAllTodos() {
        List<TodoResponseDto> responseDtoList = todoService.getAllTodos();
        if (responseDtoList.isEmpty()) {
            // Todo가 없는 경우 빈 리스트와 메시지를 반환
            return ResponseEntity.ok(new ApiResponse<>("No todos found", responseDtoList));
        }
        // Todo가 있는 경우 성공 메시지와 함께 반환
        return ResponseEntity.ok(new ApiResponse<>("Success", responseDtoList));
    }

    /**
     * 특정 ID를 가진 Todo를 조회합니다.
     *
     * @param id 조회할 Todo의 ID
     * @return 조회된 Todo 데이터를 포함하는 ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        TodoResponseDto responseDto = todoService.getTodoById(id);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 특정 ID를 가진 Todo를 수정합니다.
     *
     * @param id         수정할 Todo의 ID (URL 경로에서 가져옴)
     * @param requestDto 수정할 데이터를 담은 객체
     * @return 수정된 Todo 데이터를 포함하는 ResponseEntity
     * @throws IllegalArgumentException URL의 ID와 요청 본문의 ID가 일치하지 않는 경우
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoCreateRequestDto requestDto) {
        if (!id.equals(requestDto.getId())) {
            throw new IllegalArgumentException("URL ID와 Body ID가 일치하지 않습니다.");
        }
        TodoResponseDto responseDto = todoService.updateTodo(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 특정 ID를 가진 Todo를 삭제합니다.
     *
     * @param id 삭제할 Todo의 ID
     * @return HTTP 상태 코드 200을 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id); // Todo 삭제 요청 처리
        return new ResponseEntity<>(HttpStatus.OK); // 삭제가 성공적으로 처리된 경우 200 반환
    }
}
