package com.example.news_springbootbackend.webscrap;

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
import java.util.List;

@Configuration
public class scrapschedular {



    @Autowired
    private Newsrepository repository;

    //@Scheduled(cron="0 15 0 * * ?",zone = "Hongkong")
    @Scheduled(fixedRate = 86400*1000L)
    public void timer(){
        LocalDateTime now=LocalDateTime.now();
         LocalDate today=LocalDate.now();
        final String base_urls="https://www.bbc.com/news";
        ArrayList<String> urls=new ArrayList<String>(5);
        final String[] s={base_urls,base_urls+"/world",base_urls+"/business",base_urls+"/technology",base_urls+"/entertainment_and_arts"};
        for(String i:s){
            urls.add(i);
        }
        for(String url:urls){
        try{
            final Document document= Jsoup.connect(url).get();
            int category=urls.indexOf(url);
            final Elements links= document.getElementsByClass("gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor");
            int num=0;
            Boolean repeated_news=false;


            for (Element link:links){
                if(num>7){
                    break;
                }
                News news=new News();
                String real_url=link.attr("href");
                if(!real_url.startsWith("https")){
                    real_url="https://www.bbc.com/"+real_url;
                }
                //if(!url.equals(base_urls)){
                  //  List<News> today_topnews=repository.getnewsbydate(today);
                    //for (News topnews:today_topnews){
                      //  if(topnews.getUrl().equals(url)){
                        //    repeated_news=true;
                         //   break;
                        //}
                    //}
                //}
                if(repeated_news){
                    break;
                }
                articlecontent(real_url,news,category);
                num++;

            }
            //System.out.println(paragraph);

        }catch (Exception ex){
            System.out.println(ex);;
        }
    }}
    private void articlecontent(String url, News news,int category) {
        try {
            LocalDateTime now=LocalDateTime.now();
             LocalDate today=LocalDate.now();
            final Document document=Jsoup.connect(url).get();
            String paragraph="";


            final Elements title= document.getElementsByClass("ssrcss-15xko80-StyledHeading e1fj1fc10");
            final Elements image=document.getElementsByClass("ssrcss-evoj7m-Image ee0ct7c0");
            final Elements contents=document.getElementsByClass("ssrcss-1q0x1qg-Paragraph eq5iqo00");
            for (Element content:contents){

                paragraph+=content.text()+"\n";

            }
            //System.out.println(image.attr("src"));

            news.setTitle(title.text());
            news.setImage(image.attr("src"));
            news.setContent(paragraph);
            news.setDate(today);
            news.setUrl(url);
            news.setCategory(category);

            if (news.getTitle()!=""){
                repository.save(news);
            }
            System.out.println(now);
            System.out.println(category);
}catch(Exception ex){}}}
