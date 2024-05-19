package com.cve.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CveApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CveApiApplication.class, args);
	}

}
