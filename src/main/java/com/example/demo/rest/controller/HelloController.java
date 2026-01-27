package com.example.demo.rest.controller;

import com.example.demo.configuration.security.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        var userdetails = authenticationService.loadUserByUsername(name);
        log.info("" + userdetails);
        return "Hello, " + name + "!";
    }
}