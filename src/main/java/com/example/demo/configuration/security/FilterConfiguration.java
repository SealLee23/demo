package com.example.demo.configuration.security;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class FilterConfiguration {
    @Bean
    Filter requestValidationFilter(){
        return (request, response, filterChain) -> {
            var requestId = ((HttpServletRequest) request).getHeader("Request-Id");
            log.info("Request-Id: " + requestId);
            if(Strings.isBlank(requestId)){
                ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            filterChain.doFilter(request, response);
        };
    }

    @Bean
    Filter requestValidationFilter1(){
        return (request, response, filterChain) -> {
            var requestId = ((HttpServletRequest) request).getHeader("Request-Id");
            log.info("Request-Id 1: " + requestId);
            if(Strings.isBlank(requestId)){
                ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            filterChain.doFilter(request, response);
        };
    }
}
