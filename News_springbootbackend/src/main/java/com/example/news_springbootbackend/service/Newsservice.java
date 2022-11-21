package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.News;

import com.example.news_springbootbackend.respository.Newsrepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class Newsservice {
    @Autowired
    private Newsrepository repository;




//get method to return a list of news base on the category
    public List<News>  getarticles(int category) {
        LocalDate today=LocalDate.now();

        //get the news if today news not exist
        if(repository.getnewsbydate(today,category).size()==0){
           return repository.getnewsbydate(today.minusDays(1),category);
        }
        return repository.getnewsbydate(today,category);


    }



 //   public List<News> gettitlenews(String title){
  //      return repository.getsinglenews(title);
    //}


    public News getarticlebyid(int news_id){
        return repository.findById(news_id).orElse(null);
    }

   // public News getnews(int id){
       // return repository.findById(id).orElse(null);
   // }


    //return  a list of news which match with the matched result
    public List<News> searchnews(String input) {
        ArrayList<String> titles=new ArrayList<String>();
        String modified_input="%"+input+"%";
        List<News> foundnews= repository.searchnews(modified_input);
        List<News> filternews=new ArrayList<News>();
        int num=0;
        //check if the news repeated and return at most 8 news
        for(News news:foundnews){
            if(num==7){
                break;
            }
            if(titles.contains(news.getTitle())){
                continue;
            }else {
                titles.add(news.getTitle());
                filternews.add(news);
            }
             num++;
        }
        return filternews;
    }

    @Transactional
    public void cleannews(LocalDate minusDays) {
        repository.cleannews(minusDays);
    }
}
