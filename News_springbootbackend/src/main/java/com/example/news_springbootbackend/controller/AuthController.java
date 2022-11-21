package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.service.JpaUserservice;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


// mvn clean package  spring-boot:repackage
import org.slf4j.Logger;
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://www.sosthweb.com/")
@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final JpaUserservice tokenService;

    public AuthController(JpaUserservice tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping("/token")
    public String token(Authentication authentication){
        System.out.println("have user login");
        System.out.println(authentication);
        LOG.debug("Token requested for user: '{}",authentication.getName());
        String token=tokenService.generateToken(authentication);
        LOG.debug("Token granted {}",token);
        return token;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody JpaUser jpaUser){
        LOG.debug("Token requested for user: '{}",jpaUser.getUsername());
        String token=tokenService.signup(jpaUser);
        LOG.debug("Token granted {}",token);
        return token;
    }

    @GetMapping("/jpauser/{username}")
    public JpaUser findUserByName(@PathVariable String username){
        return tokenService.getUserByname(username);
    }

}
