package com.assignment.todo.controller;

import com.assignment.todo.dto.UserCreateRequestDto;
import com.assignment.todo.dto.UserCreateResponseDto;
import com.assignment.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = userService.createUser(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
