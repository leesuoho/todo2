package com.assignment.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * User 엔티티는 사용자 정보를 관리하기 위한 데이터베이스 테이블과 매핑된 클래스
 * 이 클래스는 BaseEntity를 상속받아 생성 및 수정 시간을 함께 관리
 */
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    /**
     * @Id: 기본 키로 설정
     * @GeneratedValue: ID를 자동으로 생성
     * GenerationType.IDENTITY: 데이터베이스의 AUTO_INCREMENT 기능을 사용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(nullable = false): 이 필드는 null 값을 허용하지 않음
     */
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * 사용자가 작성한 Todo 리스트
     *
     * @OneToMany(mappedBy = "user"): User와 Todo의 1:N 관계를 매핑
     * mappedBy = "user": Todo 엔티티의 'user' 필드에 의해 매핑됨
     * 초기화: 빈 ArrayList로 초기화하여 NPE(Null Pointer Exception)를 방지
     */
    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    /**
     * 기본 생성자: JPA 스펙에서 엔티티는 기본 생성자를 요구
     */
    public User() {
    }

    /**
     * 사용자 정보를 초기화하는 생성자
     *
     * @param username 사용자의 이름
     * @param email    사용자의 이메일 주소
     * @param password 사용자의 비밀번호
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * 사용자 정보를 업데이트하는 메서드
     *
     * @param username 변경할 사용자 이름
     * @param email    변경할 이메일 주소
     * @param password 변경할 비밀전호
     */
    public void update(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}