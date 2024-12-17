package com.assignment.todo.controller;

import com.assignment.todo.dto.UserCreateRequestDto;
import com.assignment.todo.dto.UserCreateResponseDto;
import com.assignment.todo.dto.UserResponseDto;
import com.assignment.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> responseDtoList = userService.getAllUsers();
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto responseDto = userService.getUserById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserCreateRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
