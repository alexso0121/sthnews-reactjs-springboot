package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.History;
import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.service.Historyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "https://www.sosthweb.com/")
public class Historycontroller {
    @Autowired
    private Historyservice service;

    @DeleteMapping("deletehistory/{username}")
    public String deletehistory(@PathVariable int username){
        service.deleteallhistory(username);
        return "All History has been Deleted";
    }
    @GetMapping("/gethistory/{username}")
    public List<History> gethistory(@PathVariable String username){
        return service.gethistory(username);
    }
    @PostMapping("/clicknews")
    public News click(@RequestBody History history){
        return service.getsinglenews(history);
    }

}
