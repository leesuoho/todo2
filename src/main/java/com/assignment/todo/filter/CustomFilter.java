package com.assignment.todo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * CustomFilter 클래스는 모든 HTTP 요청에 대해 필터링 작업을 수행
 * 주요 역할:
 * HTTP 요청의 URI를 로깅하여 오청 정보를 확인
 * 필터 체인을 통해 요청을 다음 단계로 전달
 *
 * @Slf4j: 로깅을 위한 Lombok 어노테이션으로, Log 객체를 자동으로 생성
 */
@Slf4j
public class CustomFilter implements Filter {
    /**
     * 요청 및 응답에 대한 필터링 작업을 수행하는 메서드
     *
     * @param servletRequest  클라이언트로부터 들어온 요청 객체
     * @param servletResponse 클라이언트에게 전달될 응답 객체
     * @param filterChain     필터 체인 객체로, 다음 필터로 요청을 전달하거나 최종 목적지로 보냄
     * @throws IOException      입출력 예외 발생 시 던짐
     * @throws ServletException 서블릿 관련 예외 발생 시 던짐
     */
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        // ServletRequest를 HttpServletRequest로 변환하여 HTTP 요청 정보를 다룰 수 있음
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 요청 URI를 가져옴
        String requestURI = httpServletRequest.getRequestURI();
        // 요청 URI를 로그로 출력
        log.info("request URI ={}", requestURI);
        // 필터 체인을 통해 요청을 다음 필터 또는 컨트롤러로 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
