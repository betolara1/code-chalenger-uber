package com.films.FilmsLocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilmsLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmsLocationApplication.class, args);
	}

}
