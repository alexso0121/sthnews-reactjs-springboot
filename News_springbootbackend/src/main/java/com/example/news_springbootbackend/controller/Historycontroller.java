package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.History;
import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.service.Historyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Historycontroller {
    @Autowired
    private Historyservice service;

    @DeleteMapping("deletehistory/{user_id}")
    public String deletehistory(@PathVariable int user_id){
        service.deleteallhistory(user_id);
        return "All History has been Deleted";
    }
    @GetMapping("/gethistory/{userid}")
    public List<History> gethistory(@PathVariable int userid){
        return service.gethistory(userid);
    }
    @PostMapping("/clicknews")
    public News click(@RequestBody History history){
        return service.getsinglenews(history);
    }

}
