package com.example.news_springbootbackend.schedule;

import com.example.news_springbootbackend.respository.Newsrepository;
import com.example.news_springbootbackend.service.Newsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDate;

//configuration class for cleaning news that are not stored and the news passed for 7 days

@Configuration
public class clearnews {
    @Autowired
     private Newsservice service ;


    @Scheduled(fixedRate = 7*86400*1000L)
    public void timer(){
        LocalDate today=LocalDate.now();
        System.out.println("start clean news");
        service.cleannews(today.minusDays(7));
    }
}
