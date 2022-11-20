package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.News;

import com.example.news_springbootbackend.respository.Newsrepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class Newsservice {
    @Autowired
    private Newsrepository repository;



    //scrap everyday news in bbc and store  it  the database
    //get the link of the specific news





//get method to get a list of news
    public List<News>  getarticles(int category) {
        LocalDate today=LocalDate.now();

        if(repository.getnewsbydate(today,category).size()==0){
           return repository.getnewsbydate(today.minusDays(1),category);
        }
        return repository.getnewsbydate(today,category);


    }



    public List<News> gettitlenews(String title){
        return repository.getsinglenews(title);
    }

    public News getarticlebyid(int news_id){
        return repository.findById(news_id).orElse(null);
    }

    public News getnews(int id){
        return repository.findById(id).orElse(null);
    }


    public List<News> searchnews(String input) {
        ArrayList<String> titles=new ArrayList<String>();
        String modified_input="%"+input+"%";
        List<News> foundnews= repository.searchnews(modified_input);
        List<News> filternews=new ArrayList<News>();
        int num=0;
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
}
