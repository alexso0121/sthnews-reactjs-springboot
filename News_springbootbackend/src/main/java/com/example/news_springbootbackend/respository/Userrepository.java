package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends JpaRepository<User,Long> {
    User findByName(String name);

    @Query(value="Select password From user Where name=?1",nativeQuery = true)
    String findpassword(String name);

    @Query(value="Select name From user Where name=?1",nativeQuery = true)
    String checkname(String name);
}
