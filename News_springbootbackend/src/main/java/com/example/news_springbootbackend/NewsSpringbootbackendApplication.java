package com.example.news_springbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class NewsSpringbootbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsSpringbootbackendApplication.class, args);
	}



}
