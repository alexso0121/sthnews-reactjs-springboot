package com.example.news_springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "store")
public class Store {

        @Id
        @GeneratedValue
        private int id;
        private int user_id;
        private int news_id;
        private String title;
        private LocalDate date;



    }

