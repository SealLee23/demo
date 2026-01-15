package com.example.demo.configuration.security;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Objects;

@Configuration
@Profile("dev")
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var userDetailsService =
                new InMemoryUserDetailsManager(user);
//        http.userDetailsService(userDetailsService);
        http.authenticationProvider(authProvider());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    AuthenticationProvider authProvider(){
        return new AuthenticationProvider() {
            @Override
            public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
                log.info(authentication.getName());
                log.info(Objects.requireNonNull(authentication.getCredentials()).toString());
                log.info(authentication.getAuthorities() + "");
                authentication.getAuthorities();
                log.info(authentication.getDetails() + "");
                return new UsernamePasswordAuthenticationToken(
                        authentication.getName(), String.valueOf(authentication.getCredentials()), List.of()
                );
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return  UsernamePasswordAuthenticationToken
                        .class
                        .isAssignableFrom(authentication);
            }
        };
    }
}
