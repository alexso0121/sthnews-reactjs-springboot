package com.example.news_springbootbackend.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    private String email;
    private String status;


}
