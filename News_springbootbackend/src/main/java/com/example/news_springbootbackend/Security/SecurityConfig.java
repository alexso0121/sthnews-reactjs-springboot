package com.example.news_springbootbackend.Security;

import net.bytebuddy.build.Plugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public InMemoryUserDetailsManager user(){
        return new InMemoryUserDetailsManager(
                User.withUsername("alex")
                        .password("{noop}password")
                        .authorities("read").build()
        );
    }

    //@Override
    //protected  boolean shouldNotFilter(HttpServletRequest request) throws SecurityException{
        //String path=request.getRequestURI();
        //return "/shownews/0".equals(path);


   // }

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                        .authorizeRequests(auth-> auth
                                .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                //sessionmanagement can direct the client to specific url if they have wrong pw
                                .sessionManagement(Session -> Session.sessionCreationPolicy((SessionCreationPolicy.STATELESS) ))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
