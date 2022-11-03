package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.service.Newsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Newscontroller {
    @Autowired
    private Newsservice service;

    @PostMapping("/clicknews")
    public News storenews(@RequestBody News news){
        return service.savenews(news);
    }
}
