package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.Store;
import com.example.news_springbootbackend.service.Storeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "https://www.sosthweb.com/")
@CrossOrigin(origins = "http://localhost:3000/")
public class Storecontroller {
    @Autowired
    private Storeservice service;



    //@CrossOrigin(origins = "https://master.d23osv0bbzg74s.amplifyapp.com")
    @PostMapping("/addstore")
    public Store addstore(@RequestBody Store store){
        return service.addstore(store);
    }

    @GetMapping("/getstore/{username}")
    public List<Store> getstore(@PathVariable String username){
        return service.getstore(username);
    }
    @DeleteMapping("/deleteallstore/{username}")
    public String deletestore(@PathVariable String username){
        service.deleteallstore(username);
        return "All stored news has been deleted";
    }
    @DeleteMapping("/deletestore/{user_id}")
    public String deletesinglestore(@PathVariable int user_id){
        service.deletesinglestore(user_id);
        return "The stored news has been deleted";
    }
}
