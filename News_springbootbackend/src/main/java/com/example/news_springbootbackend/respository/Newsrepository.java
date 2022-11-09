package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Newsrepository extends JpaRepository<News,Integer> {


    @Query(value = "SELECT * FROM news_records Where title=?1",nativeQuery = true)
    List<News> getsinglenews(String title);


    @Query(value="SELECT * from news_records where date=?1",nativeQuery = true)
    List<News> getnewsbydate(LocalDate today);
}
