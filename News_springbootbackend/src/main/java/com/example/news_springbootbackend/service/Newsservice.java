package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.News;

import com.example.news_springbootbackend.respository.Newsrepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;

@Service
public class Newsservice {
    @Autowired
    private Newsrepository repository;
    private static LocalDate today=LocalDate.now();



    //scrap everyday news in bbc and store  it  the database
    //get the link of the specific news
    public void  articlescrap(){
        final String url="https://www.bbc.com/news";
        try{
            final Document document=Jsoup.connect(url).get();
            final Elements links= document.getElementsByClass("gs-c-promo-heading gs-o-faux-block-link__overlay-link gel-pica-bold nw-o-link-split__anchor");
            int num=0;


            for (Element link:links){
                if(num>9){
                    break;
                }
                News news=new News();
                String real_url=link.attr("href");
                if(real_url.startsWith("https")!=true){
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

    //set the contents in articles and save
    private void articlecontent(String url, News news) {
        try {

            final Document document=Jsoup.connect(url).get();
            String paragraph="";

            int i=0;
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



        }catch(Exception ex){


        }


    }

//get method to get a list of news
    public List<News>  getarticles() {

        if(repository.getnewsbydate(today).size()==0){
            articlescrap();
        }
        return repository.getnewsbydate(today);


    }



    public List<News> gettitlenews(String title){
        return repository.getsinglenews(title);
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
