package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.History;
import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.respository.JpaUserrepository;
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
    private JpaUserrepository jpaUserrepository;

    @Autowired
    private Newsservice newsservice;

    public Historyservice() {
    }

    public List<History> gethistory(String username){
        return repository.findbyusername(username);
    }

    @Transactional
    public int deleteallhistory(int username){
        return repository.deleteallstored(username);
    }

    public News getsinglenews(History history) {
        JpaUser user=jpaUserrepository.findByUsername(history.getUsername()).orElse(null);
        history.setUser_id(user.getId());
        int requested_news_id=history.getNews_id();
        History old_history=repository.haveidenticalhis(history.getUser_id(), history.getTitle());
        if(old_history==null){
        repository.save(history);}
        return newsservice.getarticlebyid(requested_news_id);

    }
}
