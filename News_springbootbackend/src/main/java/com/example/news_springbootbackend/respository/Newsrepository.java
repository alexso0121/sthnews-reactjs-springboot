package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface Newsrepository extends JpaRepository<News,Long> {


}
