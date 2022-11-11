package com.example.news_springbootbackend.controller;

import com.example.news_springbootbackend.entity.User;
import com.example.news_springbootbackend.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://master.d23osv0bbzg74s.amplifyapp.com/")
public class Usercontroller {

    @Autowired
    private Userservice service;

    @GetMapping("/User/{name}")
    public User findUserByName(@PathVariable String name){
        return service.getUserByname(name);
    }

   // @CrossOrigin(origins="https://master.d23osv0bbzg74s.amplifyapp.com")
    @GetMapping("/password/{name}")
    public String findUserpassword(@PathVariable String name){
        return service.getUserpasswordByname(name);
    }

   // @CrossOrigin(origins="https://master.d23osv0bbzg74s.amplifyapp.com")
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return  service.saveuser(user);
    }

    @PutMapping("/update/{name}")
    public  User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }
}
