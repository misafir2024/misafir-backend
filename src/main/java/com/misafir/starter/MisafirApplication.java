package com.misafir.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.misafir")
@EnableJpaRepositories(basePackages = "com.misafir.repositories")
@EntityScan(basePackages = "com.misafir.entities")
public class MisafirApplication {
	public static void main(String[] args) {
		SpringApplication.run(MisafirApplication.class, args);
	}
}
