package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.News;

import com.example.news_springbootbackend.respository.Newsrepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Newsservice {
    @Autowired
    private Newsrepository repository;

    public String content(String url){
        try{
            String paragraph="";
            final Document document= Jsoup.connect(url).get();
            //final Elements title_ele =document.getElementsByClass("ssrcss-15xko80-StyledHeading e1fj1fc10");
            //final Elements description_ele =document.getElementsByClass("ssrcss-1q0x1qg-Paragraph eq5iqo00");
            final Elements contents =document.getElementsByClass("ssrcss-1q0x1qg-Paragraph eq5iqo00");
            for (Element content:contents){
                paragraph+=content.text();
            }
            return paragraph;


            //return contents.text();

        }catch (Exception ex){
            return "Error";
        }

    }
    public List<News> gettitlenews(String title){
        return repository.getsinglenews(title);
    }

    public int savenews(News news){
        String paragraph=content(news.getUrl());
        news.setContent(paragraph);
        for (News singlenews:gettitlenews(news.getTitle())){
            if (singlenews.getTitle().equals(news.getTitle())&&singlenews.getUser_id()==news.getUser_id()){
                return singlenews.getId();
            }
        }
        News storednews=repository.save(news);
        return storednews.getId();
    }
    public News getnews(int id){
        return repository.findById(id).orElse(null);
    }

    public List<News> gethistory(int userid){
        return repository.findbyuserid(userid);
    }

    public String deleteallhistory(int user_id){
        return repository.deleteallstored(user_id);
    }

}
