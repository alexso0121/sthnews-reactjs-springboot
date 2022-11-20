package com.example.news_springbootbackend.schedule;

import com.example.news_springbootbackend.Email.Emailsender;
import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
public class dumpyemail {

    @Autowired
    private JpaUserrepository jpaUserrepository;

    @Autowired
    private Emailsender emailsender;

    @Scheduled(fixedRate = 7*86400*1000L)
    public void timeer(){
        System.out.println("Start dumpy");
        List<JpaUser> allusers = jpaUserrepository.getallUsers();
        for (JpaUser user:allusers){
            emailsender.SendEmail(user.getEmail(),
                    "Just Dumpy ",
                    "Dear "+ user.getUsername()+":"+"\n"+"\n"+
                            "Nothing! I just want to wake you if you havent!\n \n"+
                            "Best Regards,\n"+
                            "Sth coop.");

        }



    }
}
