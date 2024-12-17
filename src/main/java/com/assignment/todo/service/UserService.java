package com.assignment.todo.service;

import com.assignment.todo.dto.UserCreateRequestDto;
import com.assignment.todo.dto.UserCreateResponseDto;
import com.assignment.todo.entity.User;
import com.assignment.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {

        User user = new User(requestDto.getUsername(), requestDto.getEmail());

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDto(savedUser);
    }
}
