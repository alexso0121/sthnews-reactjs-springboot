package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Newsrepository extends JpaRepository<News,Integer> {

    @Query(value="SELECT * FROM news_records WHERE user_id=?1 ORDER BY id DESC",nativeQuery = true)
    List<News> findbyuserid(int userid);
}
