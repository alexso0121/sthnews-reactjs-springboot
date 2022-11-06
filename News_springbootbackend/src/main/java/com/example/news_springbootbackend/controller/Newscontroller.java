package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import com.example.news_springbootbackend.service.Newsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Newscontroller {
    @Autowired
    private Newsservice service;

    @PostMapping("/clicknews")
    public int storenews(@RequestBody News news){
        return service.savenews(news);
    }

    @GetMapping("/getnews/{id}")
    public News getnews(@PathVariable int id){
        return service.getnews(id);
    }

    @GetMapping("/gethistory/{userid}")
    public List<News> gethistory(@PathVariable int userid){
        return service.gethistory(userid);
    }

    @DeleteMapping("deletehistory/{user_id}")
    public String deletehistory(@PathVariable int user_id){
         service.deleteallhistory(user_id);
         return "All History has been Deleted";
    }
}
