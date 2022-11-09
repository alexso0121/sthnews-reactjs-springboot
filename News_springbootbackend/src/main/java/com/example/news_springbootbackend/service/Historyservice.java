package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.History;
import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.respository.historyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class Historyservice {
    @Autowired
    private historyrepository repository;

    @Autowired
    private Newsservice newsservice;

    public List<History> gethistory(int userid){
        return repository.findbyuserid(userid);
    }

    @Transactional
    public int deleteallhistory(int user_id){
        return repository.deleteallstored(user_id);
    }

    public News getsinglenews(History history) {
        int requested_news_id=history.getNews_id();
        repository.save(history);
        return newsservice.getarticlebyid(requested_news_id);

    }
}
