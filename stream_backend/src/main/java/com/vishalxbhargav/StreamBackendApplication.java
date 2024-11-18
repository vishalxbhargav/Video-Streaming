package com.vishalxbhargav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class StreamBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamBackendApplication.class, args);
	}

}
