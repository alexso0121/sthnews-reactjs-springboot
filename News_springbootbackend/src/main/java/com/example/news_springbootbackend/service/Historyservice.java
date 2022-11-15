package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.History;
import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.respository.historyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//For logic in handling Users' history
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
        History old_history=repository.haveidenticalhis(history.getUser_id(), history.getTitle());
        if(old_history==null){
        repository.save(history);}
        return newsservice.getarticlebyid(requested_news_id);

    }
}
