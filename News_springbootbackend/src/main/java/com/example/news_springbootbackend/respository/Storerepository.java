package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Storerepository extends JpaRepository<Store,Integer> {
    @Query(value = "select * from store where user_id=?1 ORDER BY id DESC LIMIT 8",nativeQuery = true)
    List<Store> getstore(int user_id);
    @Query(value="Select * from store where news_id=?1 AND user_id=?2 ",nativeQuery = true)
    Store getsinglestore(int news_id ,int user_id);

    @Modifying
    @Query(value = "delete from store where user_id=?1",nativeQuery = true)
    int deleteallstore(int user_id);
}