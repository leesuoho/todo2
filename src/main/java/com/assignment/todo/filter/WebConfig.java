package com.assignment.todo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * WebConfig 클래스는 필터를 등록하는 설정 클래스
 * Spring Boot 애플리케이션에서 서블릿 필터를 설정하고 관리할 수 있게 해줌
 * 주요기능:
 * CustomFilter와 LoginFilter를 애플리케이션에 등록하여 필터 체인에서 사용할 수 있도록 설정
 */
@Configuration
public class WebConfig {

    /**
     * CustomFilter를 등록하는 Bean 메서드
     *
     * @return FilterRegistrationBean<CustomFilter> CustomFilter 필터 등록 객체
     */
//    @Bean
//    public FilterRegistrationBean customFilter() {
//        // CustomFilter 필터를 등록하고 필터 순서를 1로 설정 (순서는 숫자가 낮을수록 먼저 실행)
//        FilterRegistrationBean<CustomFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new CustomFilter()); // 필터 객체 설정
//        filterRegistrationBean.setOrder(1); // 필터의 실행 순서 설정
//        filterRegistrationBean.addUrlPatterns("/*"); // 모든 URL에 대해 필터를 적용
//
//        // 필터 등록 정보를 반환
//        return filterRegistrationBean;
//    }

    //실질적으로 적용
    @Bean
    public FilterRegistrationBean loginFilter() {
        // LoginFilter 필터를 등록하고 필터 순서를 2로 설정 (1번 필터 후 실행)
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
