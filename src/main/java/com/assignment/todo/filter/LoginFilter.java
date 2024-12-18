package com.assignment.todo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

/**
 * LoginFilter 클래스는 사용자가 로그인된 상태에서만 특정 리소스에 접근할 수 있도록 제한하는 필터
 * 주요 역할:
 * 화이트리스트(로그인 없이 접근 가능한 URI) 외의 요청에 대해 로그인 여부를 체크
 * 로그인되지 않은 경우, 로그인 페이지로 리다이렉트하거나 오류를 발생시킴
 */
@Slf4j
public class LoginFilter implements Filter {

    /**
     * 화이트리스트에 정의된 URI 목록
     * 화이트리스트에 포함된 URI는 로그인 여부와 관계없이 접근이 가능
     */
    private static final String[] WHITE_LIST = {"/", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 필터가 샐행될 때마다 로그로 요청 URI 출력
        log.info("로그인 필터 로직 실행");

        // 요청 URI가 화이트리스트에 포함되지 않으면 로그인 여부를 확인
        if (!isWhiteList(requestURI)) {
            // 세션이 없거나, 세션에 "user" 속성이 없다면 로그인되지 않은 것으로 간주
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                // 로그인하지 않았다면 예외를 던짐
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        // 필터 체인을 통해 요청을 다음 필터 또는 컨트롤러로 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 화이트리스트에 해당하는 URI인지 확인하는 메서드
     *
     * @param requestURI 요청 URI
     * @return 화이트리스트에 포함된 URI면 true, 그렇지 않으면 false
     */
    private boolean isWhiteList(String requestURI) {
        // PatternMatchUtils.simpleMatch: 패턴 매칭을 통해 URI가 화이트리스트에 포함되는지 확인
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
