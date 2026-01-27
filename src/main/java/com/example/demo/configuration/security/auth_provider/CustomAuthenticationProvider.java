package com.example.demo.configuration.security.auth_provider;

import com.example.demo.configuration.security.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationService userDetailsService;

    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();;

    @Override
    public @NonNull Authentication authenticate(@NonNull Authentication authentication) throws AuthenticationException {
        try {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            log.info("Request USERNAME: " + username);
            log.info("Request PASSWORD: " + password);
            UserDetails u = userDetailsService.loadUserByUsername(username);
            log.info("User from DB: " + u);
            if (passwordEncoder.matches(password, u.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        u.getAuthorities());
            }
        }catch(Exception e){
            log.error("Something went wrong!", e);
        }
        throw new BadCredentialsException("username or password is incorrect.");
    }

    @Override
    public boolean supports(@NonNull Class<?> authentication) {
        return  UsernamePasswordAuthenticationToken
                .class.equals(authentication);
    }
}
