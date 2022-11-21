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
/*


        #mysql-jpa
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.url=jdbc:mysql://#####/newsweb
        spring.datasource.username=###
        spring.datasource.password=###
        spring.jpa.show-sql=true
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
        server.port=5000


        #jwb t
        rsa.private-key=classpath:certs/private.pem
        rsa.public-key=classpath:certs/public.pem

        #logging for web
        logging.level.org.springframework.security=DEBUG

        #thymeleaf(optional)
        spring.thymeleaf.prefix=classpath:/templates/
        spring.thymeleaf.enabled=true
        spring.thymeleaf.suffix=.html
        spring.application.name=Bootstrap Spring Boot
        spring.web.resources.static-locations=classpath:/static/

        #email
        spring.mail.host=smtp.gmail.com
        spring.mail.port=587
        spring.mail.username=#####
        spring.mail.password=#####
        spring.mail.properties.mail.smtp.auth=true
        spring.mail.properties.mail.smtp.starttls.enable=true   */
