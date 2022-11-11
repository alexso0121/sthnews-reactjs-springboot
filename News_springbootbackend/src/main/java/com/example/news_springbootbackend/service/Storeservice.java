package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.Store;
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

    public Store getsinglestore(int news_id){
        return repository.getsinglestore(news_id);
    }
    public Store addstore(Store store){
        int news_id=store.getNews_id();
        if(getsinglestore(news_id)!=null){
            return null;
        }
        LocalDate today=LocalDate.now();
        store.setDate(today);
        return repository.save(store);
    }

    public List<Store> getstore(int user_id){
        return repository.getstore(user_id);
    }
    @Transactional
    public String deleteallstore(int user_id){
        repository.deleteallstore(user_id);
        return " All deleted ";
    }

    public String deletesinglestore(int id){
        repository.deleteById(id);
        return "";
    }
}
