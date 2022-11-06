package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.Store;
import com.example.news_springbootbackend.service.Storeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Storecontroller {
    @Autowired
    private Storeservice service;



    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addstore")
    public Store addstore(@RequestBody Store store){
        return service.addstore(store);
    }

    @GetMapping("/getstore/{user_id}")
    public List<Store> getstore(@PathVariable int user_id){
        return service.getstore(user_id);
    }
    @DeleteMapping("/deleteallstore/{user_id}")
    public String deletestore(@PathVariable int user_id){
        service.deleteallstore(user_id);
        return "All stored news has been deleted";
    }
    @DeleteMapping("/deletestore/{id}")
    public String deletesinglestore(@PathVariable int id){
        service.deletesinglestore(id);
        return "The stored news has been deleted";
    }
}
