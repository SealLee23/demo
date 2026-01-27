package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.util.List;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		List.of("","").stream()
//				.filter(s -> !StringUtils.hasText(s))
//				.findAny().
//				.findAny()
//				.allMatch()
	}
}
