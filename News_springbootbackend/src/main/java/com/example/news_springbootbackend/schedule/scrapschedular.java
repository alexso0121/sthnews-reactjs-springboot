package com.example.news_springbootbackend.schedule;

import com.example.news_springbootbackend.entity.News;
import com.example.news_springbootbackend.respository.Newsrepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//automatically web scrap bbc news everyday



@Configuration
public class scrapschedular {

    @Autowired
    private Newsrepository repository;


    //the timer for activating the function everyday
    @Scheduled(fixedRate = 86400*1000L)
    public void timer(){
        System.out.println("Start scraping news");
        //LocalDateTime now=LocalDateTime.now();
        // LocalDate today=LocalDate.now();
        final String base_urls="https://www.bbc.com/news";
        ArrayList<String> urls=new ArrayList<String>(5);

        //Adding different category urls in the arraylist
        final String[] s={base_urls,base_urls+"/world",base_urls+"/business",base_urls+"/technology",base_urls+"/entertainment_and_arts"};
        for(String i:s){
            urls.add(i);
        }

        //Loop the Web scraping by the urls
        for(String url:urls){
        try{
            final Document document= Jsoup.connect(url).get();
            int category=urls.indexOf(url);
            //web scrap the news link in each category
            final Elements links= document.getElementsByClass("gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor");
            int num=0;

            ArrayList<String> array=new ArrayList<String>();

            //loop through the link in each category
            for (Element link:links){
                //get 6 news
                if(num>7){
                    break;
                }
                //build a new "News" class for putting the data
                News news=new News();
                //get the href attribute in a tag
                String real_url=link.attr("href");

                //prevent replicate news in the category
                if(array.contains(real_url)){
                    System.out.println("repeated");
                    continue;
                }else{num++;}
                array.add(real_url);

                if(!real_url.startsWith("https")){
                    real_url="https://www.bbc.com/"+real_url;
                }


                articlecontent(real_url,news,category);


            }
            //System.out.println(paragraph);

        }catch (Exception ex){
            System.out.println(ex);;
        }
    }}

    //web scrap the details for each article
    //add the data scrap into the empty "News" entity
    private void articlecontent(String url, News news,int category) {
        try {
            //Localdate must be inside the method,so that the current the is generated
            LocalDateTime now=LocalDateTime.now();
             LocalDate today=LocalDate.now();
            final Document document=Jsoup.connect(url).get();
            String paragraph="";


            final Elements title= document.getElementsByClass("ssrcss-15xko80-StyledHeading e1fj1fc10");
            final Elements image=document.getElementsByClass("ssrcss-evoj7m-Image ee0ct7c0");
            final Elements contents=document.getElementsByClass("ssrcss-1q0x1qg-Paragraph eq5iqo00");

            for (Element content:contents){
                //the "/n/" is used for paragraph manually by frontend
                paragraph+=content.text()+"/n/";

            }

            //add data into the empty "News" entity
            news.setTitle(title.text());
            news.setImage(image.attr("src"));
            news.setContent(paragraph);
            news.setDate(today);
            news.setUrl(url);
            news.setCategory(category);

            //eliminate the news that cannot show title
            if (news.getTitle()!=""){
                repository.save(news);
            }
            System.out.println(now);
            System.out.println(category);
}catch(Exception ex){}}}
