package com.example.demo.configuration.security.service;

import com.example.demo.dao.AuthorityRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Authority;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private @Autowired UserRepository userRepository;

    private @Autowired AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        assert userRepository != null;
        assert authorityRepository != null;
        var user = userRepository.findByUsername(username).get(0);
        var authorities = authorityRepository.findByUsername(username);
        log.info("User: " + user);
        log.info("Authorities: " + authorities);
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
