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

@Configuration
public class scrapschedular {
    private static LocalDate today=LocalDate.now();
    private static  LocalDateTime now=LocalDateTime.now();

    @Autowired
    private Newsrepository repository;

    @Scheduled(cron="0 1 0 * * ?",zone = "Hongkong")
    //@Scheduled(fixedRate = 200000L)
    public void timer(){
        final String url="https://www.bbc.com/news";
        try{
            final Document document= Jsoup.connect(url).get();
            final Elements links= document.getElementsByClass("gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor");
            int num=0;


            for (Element link:links){
                if(num>9){
                    break;
                }
                News news=new News();
                String real_url=link.attr("href");
                if(!real_url.startsWith("https")){
                    real_url="https://www.bbc.com/"+real_url;
                }
                articlecontent(real_url,news);
                num++;

            }
            //System.out.println(paragraph);

        }catch (Exception ex){
            System.out.println(ex);;
        }
    }
    private void articlecontent(String url, News news) {
        try {

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

            if (news.getTitle()!=""){
                repository.save(news);
            }
            System.out.println(now);
}catch(Exception ex){}}}
