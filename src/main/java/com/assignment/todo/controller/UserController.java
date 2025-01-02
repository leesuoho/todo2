package com.assignment.todo.controller;

import com.assignment.todo.dto.*;
import com.assignment.todo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserController는 사용자 관련 요청을 처리하는 REST API 컨트롤러입니다.
 * 클라이언트로부터 사용자 관련 요청을 받아서 UserService를 통해 작업을 처리합니다.
 */
@RestController
@RequestMapping("/users") // 모든 요청은 "/users" 경로로 시작합니다.
@RequiredArgsConstructor // final로 선언된 의존성을 자동으로 주입해주는 Lombok 어노테이션
public class UserController {

    private final UserService userService; // 사용자 비즈니스 로직을 처리하는 서비스 클래스
    private final DefaultErrorAttributes errorAttributes;

    /**
     * 새로운 사용자를 생성합니다.
     *
     * @param requestDto 사용자 생성 요청 데이터를 담은 객체
     * @return 생성된 사용자 정보를 담은 응답 객체
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserCreateResponseDto>> createUser(@Valid @RequestBody UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = userService.createUser(requestDto);
        return ResponseEntity.ok(new ApiResponse<>("회원가입 되었습니다.", responseDto)); // HTTP 상태 코드 200과 함께 응답 데이터
    }

    /**
     * 모든 사용자를 조회합니다.
     *
     * @return 모든 사용자 목록을 포함한 응답 객체
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> responseDtoList = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>("회원정보가 조회되었습니다.", responseDtoList)); // 사용자 목록을 HTTP 상태 코드 200과 함께 반환
    }

    /**
     * 특정 ID를 가진 사용자를 조회합니다.
     *
     * @param id 조회할 사용자의 ID
     * @return 해당 사용자의 정보를 포함한 응답 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable Long id) {
        UserResponseDto responseDto = userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>("회원정보가 조회되었습니다.", responseDto)); // 조회된 사용자 정보를 반환
    }

    /**
     * 특정 ID를 가진 사용자의 정보를 수정합니다.
     *
     * @param id         수정할 사용자의 ID (URL 경로에서 가져옴)
     * @param requestDto 수정할 사용자 데이터를 담은 객체
     * @return 수정된 사용자 정보를 포함한 응답 객체
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long id, @RequestBody UserCreateRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.ok(new ApiResponse<>("회원정보가 변경되었습니다.", responseDto)); // 수정된 사용자 정보를 반환
    }

    /**
     * 특정 ID를 가진 사용자를 삭제합니다.
     *
     * @param id 삭제할 사용자의 ID
     * @return HTTP 상태 코드 200을 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id); // 사용자 삭제 요청 처리
        return ResponseEntity.ok(new ApiResponse<>("계정이 삭제되었습니다.", null)); // 삭제가 성공적으로 처리된 경우 200 반환
    }

    /**
     * 사용자 로그인 기능을 처리합니다.
     *
     * @param loginRequestDto 로그인 요청에 필요한 이메일과 비밀번호를 담은 객체
     * @param request         HTTP 요청 객체, 세션을 관리하는 데 사용
     * @return 로그인 성공 메시지
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        // 로그인 요청을 처리하고 응답을 생성
        LoginResponseDto responseDto = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        // 세션을 생성하거나 기존 세션을 가져와서 사용자 정보를 저장
        HttpSession session = request.getSession();
        session.setAttribute("user", responseDto);

        return ResponseEntity.ok(new ApiResponse<>("로그인 성공", responseDto)); // 로그인 성공 메시지 반환
    }

    /**
     * 사용자 로그아웃 기능을 처리합니다.
     *
     * @param request HTTP 요청 객체, 세션을 관리하는 데 사용
     * @return 로그아웃 성공 메시지
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request) {
        // 현재 세션이 존재하면 무효화하여 로그아웃 처리
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok(new ApiResponse<>("로그아웃 성공", null)); // 로그아웃 성공 메시지 반환
    }
}
