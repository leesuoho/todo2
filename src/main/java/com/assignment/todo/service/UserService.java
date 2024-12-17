package com.assignment.todo.service;

import com.assignment.todo.dto.UserCreateRequestDto;
import com.assignment.todo.dto.UserCreateResponseDto;
import com.assignment.todo.dto.UserResponseDto;
import com.assignment.todo.entity.User;
import com.assignment.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {

        User user = new User(requestDto.getUsername(), requestDto.getEmail());

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDto(savedUser);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다: " + id));
        return new UserResponseDto(user);
    }

    public UserResponseDto updateUser(Long id, UserCreateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다." + id));
        user.update(requestDto.getUsername(), requestDto.getEmail());
        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }
}
