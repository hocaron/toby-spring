package com.study.tobyspring.ping;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.study.tobyspring.config.annotation.MySpringBootApplication;

@MySpringBootApplication
public class TobySpringApplication {

	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			String name = environment.getProperty("my.name");
			System.out.println("my.name " + name);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TobySpringApplication.class, args);
	}
}

