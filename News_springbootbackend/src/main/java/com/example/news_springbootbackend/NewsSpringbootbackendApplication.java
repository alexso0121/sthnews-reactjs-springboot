package com.example.news_springbootbackend;

import com.example.news_springbootbackend.Security.RsaKeyProp;
import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import com.example.news_springbootbackend.respository.Userrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.GrantedAuthority;
import com.example.news_springbootbackend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@EnableConfigurationProperties(RsaKeyProp.class)
@SpringBootApplication
@EnableScheduling
public class NewsSpringbootbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsSpringbootbackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JpaUserrepository users, PasswordEncoder encoder) {


		return args -> {
			//users.save(new JpaUser("user2",encoder.encode("password2"),"ROLE_USER"));
			//users.save(new JpaUser("admin",encoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
			//users.save(new Post("Hello, World!","hello-world","Welcome to my new blog!","Dan Vega"));

		};
	}



}
