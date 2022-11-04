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
@Table(name = "news_records")
public class News {

    @Id
    @GeneratedValue
    private int id;
    private int user_id;
    private String title;
    private String description;
    private String Url;
    private String content;
    private String image;
    private LocalDate date;

}