package com.assignment.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * BaseEntity는 모든 엔티티 클래스에 공통적으로 적용될 생성 및 수정 시간을 관리하기 위한 기본 클래스
 * <p>
 * 주요기능:
 * createdAt: 엔티티가 생성된 시간을 자동으로 기록
 * modifiedAt: 엔티티가 마지막으로 수정된 시간을 자동으로 기록
 *
 * @MappedSuperclass 어노테이션을 통해 이 클래스를 상속받는 엔티티 클래스에 공통 필드로 추가
 * @EntityListeners 어노테이션으로 스프링 데이터 JPA의 Auditing 기능을 활성화
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * 엔티티가 생성된 날짜 및 시간
     *
     * @CreatedDate: 엔티티가 처음 저장될 때 자동으로 값이 설정
     * updatable = false: 생성 이후에는 수정할 수 없음
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * 엔티티가 마지막으로 수정된 날짜 및 시간
     *
     * @LastModifiedDate: 엔티티가 수정될 때 자동으로 값이 변경
     */
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
