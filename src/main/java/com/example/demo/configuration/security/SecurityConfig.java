package com.example.demo.configuration.security;

import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Profile("dev")
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    @Qualifier("requestValidationFilter")
//    private Filter validationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) {
        http.httpBasic(Customizer.withDefaults());
//        http.addFilterBefore(validationFilter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );
//        http.userDetailsService(testUserDetailsService());
        log.info(authenticationProvider.getClass().toString());
        http.authenticationProvider(authenticationProvider);
        return http.build();
    }
}
