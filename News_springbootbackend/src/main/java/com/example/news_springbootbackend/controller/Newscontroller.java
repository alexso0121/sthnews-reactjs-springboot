package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import com.example.news_springbootbackend.service.Newsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://www.sosthweb.com/")
public class Newscontroller {
    @Autowired
    private Newsservice service;
    //@CrossOrigin(origins = "https://www.sosthweb.com/")
    @GetMapping("/shownews/{input}")
    public List<News> storenews(@PathVariable String input){

        try{
            int category=Integer.parseInt(input);
            return service.getarticles(category);}
        catch (Exception exception){
            return service.searchnews(input);
        }
    }

    @GetMapping("/getnews/{id}")
    public News getnews(@PathVariable int id){

            return service.getarticlebyid(id);

    }




}
