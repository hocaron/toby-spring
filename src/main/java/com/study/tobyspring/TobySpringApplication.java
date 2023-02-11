package com.study.tobyspring;

import org.springframework.boot.SpringApplication;

import com.study.tobyspring.annotation.MySpringBoot;

@MySpringBoot
public class TobySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TobySpringApplication.class, args);
	}
}
