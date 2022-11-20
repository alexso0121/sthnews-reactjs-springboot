package com.example.news_springbootbackend.schedule;

import com.example.news_springbootbackend.respository.Newsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Configuration
public class clearnews {
    @Autowired
    private Newsrepository newsrepository;

    @Transactional
    @Scheduled(fixedRate = 7*86400*1000L)
    public void timer(){
        LocalDate today=LocalDate.now();
        System.out.println("start clean news");
        newsrepository.cleannews(today.minusDays(7));
    }
}
