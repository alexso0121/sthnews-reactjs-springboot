package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.History;
import com.example.news_springbootbackend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface historyrepository extends JpaRepository<History,Integer> {
    @Query(value="SELECT * FROM history WHERE user_id=?1 ORDER BY id DESC",nativeQuery = true)
    List<History> findbyuserid(int userid);

    @Modifying
    @Query(value="delete from history where user_id=?1",nativeQuery = true)
    int deleteallstored(int user_id);
}
