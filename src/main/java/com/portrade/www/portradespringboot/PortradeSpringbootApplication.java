package com.portrade.www.portradespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PortradeSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortradeSpringbootApplication.class, args);
	}

}
