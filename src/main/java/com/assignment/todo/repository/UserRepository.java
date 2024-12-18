package com.assignment.todo.repository;

import com.assignment.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository 인터페이스는 JPA를 이용해 User 엔티티에 대한 데이터베이스 연산을 처리
 * JpaRepository를 확장함으로써 기본적인 CRUD 작업과 페이징, 정렬 등을 자동으로 제공받을 수 있다.
 * JpaRepository<User, Long>에서:
 * User는 엔티티 클래스 이름
 * Long은 User 엔티티의 기본 키 타입
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 이메일을 이용하여 사용자 조회
     *
     * @param email 사용자의 이메일
     * @return 이메일에 해당하는 사용자
     */
    Optional<User> findByEmail(String email);
}
