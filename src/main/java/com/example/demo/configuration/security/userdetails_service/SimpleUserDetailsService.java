package com.example.demo.configuration.security.userdetails_service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class SimpleUserDetailsService extends InMemoryUserDetailsManager {
    public SimpleUserDetailsService(){
        super(User.withUsername("john")
            .password("12345")
            .authorities("read")
            .build());
    }
}