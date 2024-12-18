package com.assignment.todo.service;

import com.assignment.todo.dto.LoginResponseDto;
import com.assignment.todo.dto.UserCreateRequestDto;
import com.assignment.todo.dto.UserCreateResponseDto;
import com.assignment.todo.dto.UserResponseDto;
import com.assignment.todo.entity.User;
import com.assignment.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserService 클래스는 User와 관련된 비즈니스 로직을 처리하는 서비스 클래스
 * User의 생성, 조회, 수정, 삭제, 로그인 등의 기능을 담당
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // User 엔티티와 데이터베이스 상호작용을 담당하는 리포지토리

    /**
     * 새로운 사용자를 생성하는 메서드
     *
     * @param requestDto 사용자 생성 요청 데이터
     * @return 생성된 사용자에 대한 응답 데이터
     */
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        // 요청된 데이터를 사용하여 User 객체 생성
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        // 생성된 User 객체를 데이터베이스에 저장
        User savedUser = userRepository.save(user);
        // 저장된 User 객체를 응답 DTO로 변환하여 반환
        return new UserCreateResponseDto(savedUser);
    }

    /**
     * 모든 사용자 목록을 조회하는 메서드
     *
     * @return 사용자 목록을 반환하는 응답 데이터
     */
    public List<UserResponseDto> getAllUsers() {
        // 모든 User 엔티티를 조회
        List<User> users = userRepository.findAll();
        // User 객체들을 UserResponseDto로 변환하여 리스트로 반환
        return users.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 특정 ID에 해당하는 사용자 정보를 조회하는 메서드
     *
     * @param id 조회할 사용자 ID
     * @return 조회된 사용자에 대한 응답 데이터
     */
    public UserResponseDto getUserById(Long id) {
        // 사용자 ID로 User 엔티티를 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다: " + id));
        // 조회된 User 객체를 응답 DTO로 변환하여 반환
        return new UserResponseDto(user);
    }

    /**
     * 사용자의 정보를 수정하는 메서드
     *
     * @param id         수정할 사용자 ID
     * @param requestDto 수정할 사용자 데이터
     * @return 수정된 사용자에 대한 응답 데이터
     */
    public UserResponseDto updateUser(Long id, UserCreateRequestDto requestDto) {
        // 수정할 사용자 ID로 User 엔티티를 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다: " + id));
        // 사용자 정보를 업데이트
        user.update(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        // 업데이트된 User 객체를 저장하고 반환
        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }

    /**
     * 특정 사용자를 삭제하는 메서드
     *
     * @param id 삭제할 사용자 ID
     */
    public void deleteUser(Long id) {
        // 삭제할 사용자 ID로 User 엔티티를 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저를 찾을 수 없습니다: " + id));
        // 조회된 User를 데이터베이스에서 삭제
        userRepository.delete(user);
    }

    /**
     * 사용자가 로그인할 때 호출되는 메서드
     *
     * @param email    사용자의 이메일
     * @param password 사용자의 비밀번호
     * @return 로그인에 성공한 사용자 정보
     */
    public LoginResponseDto login(String email, String password) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 이메일입니다."));

        // 비밀번호 일치 여부 확인
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공시 사용자 ID와 이름을 반환
        return new LoginResponseDto(user.getId(), user.getUsername());
    }
}
