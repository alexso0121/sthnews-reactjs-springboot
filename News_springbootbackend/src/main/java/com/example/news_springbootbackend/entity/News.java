package com.example.news_springbootbackend.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "news_records")
public class News {

    @Id
    @GeneratedValue
    //@OneToOne(cascade = {CascadeType.REMOVE})
    //@JoinColumn(name = "History_news_id")
    private int id;
    private String title;
    private String Url;
    private String content;
    private String image;
    private LocalDate date;
    private int category;
    private String isstored;

}