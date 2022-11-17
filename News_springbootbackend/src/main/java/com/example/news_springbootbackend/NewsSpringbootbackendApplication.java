package com.example.news_springbootbackend;

import com.example.news_springbootbackend.Security.RsaKeyProp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties(RsaKeyProp.class)
@SpringBootApplication
@EnableScheduling
public class NewsSpringbootbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsSpringbootbackendApplication.class, args);
	}



}
