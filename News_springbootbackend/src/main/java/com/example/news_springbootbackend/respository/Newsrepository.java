package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Newsrepository extends JpaRepository<News,Integer> {


    @Query(value = "SELECT * FROM news_records Where title=?1",nativeQuery = true)
    List<News> getsinglenews(String title);


    @Query(value="SELECT * from news_records where date=?1 AND category=?2 Order by id",nativeQuery = true)
    List<News> getnewsbydate(LocalDate today,int category);

    @Query(value="SELECT * FROM news_records WHERE title LIKE ?1 ORDER BY date DESC",nativeQuery = true)
    List<News> searchnews(String input);

    @Modifying
    @Query(value = "DELETE FROM newsweb.news_records WHERE id = ?1 ",nativeQuery = true)
    void cleannews(int id);

    @Query(value = "SELECT * FROM newsweb.news_records WHERE date<= ?1 AND isstored IS NULL",nativeQuery = true)
    List<News> findoutdatenews(LocalDate minusDays);
}
