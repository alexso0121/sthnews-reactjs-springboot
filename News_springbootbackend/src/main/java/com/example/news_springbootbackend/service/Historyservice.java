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

    //request the history for the client
    public List<History> gethistory(String username){
        return repository.findbyusername(username);
    }

    //delete all the history for the client
    @Transactional
    public int deleteallhistory(int username){
        return repository.deleteallstored(username);
    }

    //showing  news if the client click the news
    public News getsinglenews(History history) {
        //find the client who access to the news
        JpaUser user=jpaUserrepository.findByUsername(history.getUsername()).orElse(null);
        //set the user_id
        history.setUser_id(user.getId());
        //the news_id of the client who click the news
        int requested_news_id=history.getNews_id();

        //check if the client click again the news else save it as history
        History old_history=repository.haveidenticalhis(history.getUser_id(), history.getTitle());
        if(old_history==null){
        repository.save(history);}

        //returning the news that the client click
        return newsservice.getarticlebyid(requested_news_id);

    }
}
