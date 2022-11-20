package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import com.example.news_springbootbackend.respository.Newsrepository;
import com.example.news_springbootbackend.respository.Storerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class Storeservice {
    @Autowired
    private Storerepository repository;

    @Autowired
    private JpaUserrepository jpaUserrepository;

    @Autowired
    private Newsrepository newsrepository;

    //get the news with specific userid and news_id
    public Store getsinglestore(int news_id,int user_id){
        return repository.getsinglestore(news_id,user_id);
    }

    //store the news
    public Store addstore(Store store){
        int news_id=store.getNews_id();
        JpaUser temp=jpaUserrepository.findByUsername(store.getUsername()).orElse(null);
        store.setUser_id(temp.getId());
        int user_id= store.getUser_id();
        //check if replicated else save it
        if(getsinglestore(news_id,user_id)!=null){
            return null;
        }
        //set the isstored to prevent auto scheduler delete the news
        News storednews=newsrepository.findById(news_id).orElse(null);
        storednews.setIsstored("true");
        LocalDate today=LocalDate.now();
        store.setDate(today);
        return repository.save(store);
    }

    //return all the stored news of the client
    public List<Store> getstore(String username){
        return repository.getstore(username);
    }

    //delete all stored news of the client
    @Transactional
    public String deleteallstore(String username){
        repository.deleteallstore(username);
        return " All deleted ";
    }

    //delete a specific store
    public String deletesinglestore(int id){
        repository.deleteById(id);
        return "";
    }
}
