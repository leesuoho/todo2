package com.assignment.todo.repository;

import com.assignment.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TodoRepository 인터페이스는 JPA를 이용해 Todo 엔티티에 대한 데이터베이스 연산을 처리
 * JpaRepository를 확장함으로써 기본적인 CRUD 작업과 페이징, 정렬 등을 자동으로 제공받을 수 있다.
 * JpaRepository<Todo, Long>에서:
 * Todo는 엔티티 클래스 이름
 * Long은 Todo 엔티티의 기본 키 타입
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // JpaRepository가 이미 기본적인 CRUD 메서드를 제공하므로, 추가적인 메서드를 작성하지 않아도됨
}
