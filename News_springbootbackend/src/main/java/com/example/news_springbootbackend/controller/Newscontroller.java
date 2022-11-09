package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import com.example.news_springbootbackend.service.Newsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://master.d23osv0bbzg74s.amplifyapp.com/")
public class Newscontroller {
    @Autowired
    private Newsservice service;

    @GetMapping("/shownews")
    public List<News> storenews(){
        return service.getarticles();
    }

    @GetMapping("/getnews/{id}")
    public News getnews(@PathVariable int id){
        return service.getnews(id);
    }




}
