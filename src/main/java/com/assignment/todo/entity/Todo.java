package com.assignment.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Todo 엔티티는 할 일 데이터를 관리하기 위한 데이터베이스 테이블과 매핑된 클래스
 * 이 클래스는 BaseEntity를 상속받아 생성 및 수정 시간도 함께 관리
 */
@Getter
@Entity
@Table(name = "todos") // 데이터베이스 테이블 이름을 "todos"로 지정
public class Todo extends BaseEntity {

    /**
     * @ID: 기본 키로 설정
     * @GeneratedValue: ID를 자동으로 생성
     * GenerationType.IDENTITY: 데이터베이스의 AUTO_INCREMENT 기능을 사용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(nullable = false): 이 필드는 null 값을 허용하지 않음
     */
    @Setter
    @Column(nullable = false)
    private String title;

    /**
     * @Column(columnDefinition = "Longtext"): 데이터베이스에서 Longtext 타입으로 저장
     */
    @Setter
    @Column(columnDefinition = "longtext")
    private String contents;

    /**
     * @ManyToOne: 다대일 관계를 매핑. 여러 Todo가 하나의 User에 속할 수 있음
     * @JoinColumn(name = "user_id"): 외래 키로 "user_id"를 사용
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo() {
    }

    public Todo(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
}
