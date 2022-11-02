package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.User;
import com.example.news_springbootbackend.respository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userservice {
    @Autowired
    private Userrepository repository;

    public User saveuser(User user){
        return repository.save(user);
    }

    public User getUserByname(String name){
        return repository.findByName(name);
    }

    public String getUserpasswordByname(String name){return repository.findpassword(name);}
}
