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

    public Store getsinglestore(int news_id,int user_id){
        return repository.getsinglestore(news_id,user_id);
    }
    public Store addstore(Store store){
        int news_id=store.getNews_id();
        JpaUser temp=jpaUserrepository.findByUsername(store.getUsername()).orElse(null);
        store.setUser_id(temp.getId());
        int user_id= store.getUser_id();
        if(getsinglestore(news_id,user_id)!=null){
            return null;
        }
        News storednews=newsrepository.findById(news_id).orElse(null);
        storednews.setIsstored("true");
        LocalDate today=LocalDate.now();
        store.setDate(today);
        return repository.save(store);
    }

    public List<Store> getstore(String username){
        return repository.getstore(username);
    }
    @Transactional
    public String deleteallstore(String username){
        repository.deleteallstore(username);
        return " All deleted ";
    }

    public String deletesinglestore(int id){
        repository.deleteById(id);
        return "";
    }
}
