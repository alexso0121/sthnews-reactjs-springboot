package com.example.news_springbootbackend.respository;

import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserrepository extends CrudRepository<JpaUser,Long> {
    Optional<JpaUser> findByUsername(String username);

    @Query(value="SELECT * FROM newsweb.jpa_user;",nativeQuery = true)
    List<JpaUser> getallUsers();

}
