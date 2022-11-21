package com.example.news_springbootbackend.schedule;

import com.example.news_springbootbackend.Email.Emailsender;
import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

//send a dumpy email for every registered user every week

@Configuration
public class dumpyemail {

    @Autowired
    private JpaUserrepository jpaUserrepository;

    @Autowired
    private Emailsender emailsender;

    @Scheduled(fixedRate = 6*86400*1000L)
    public void timeer(){
        System.out.println("Start dumpy");
        List<JpaUser> allusers = jpaUserrepository.getallUsers();
        for (JpaUser user:allusers){
            emailsender.SendEmail(user.getEmail(),
                    "Just a Dump Email ",
                    "Dear "+ user.getUsername()+":"+"\n"+"\n"+
                            "NOTHING! I just want to faking wake you up if you are not!\n " +
                            "@this email is automatically sent every week\n \n"+
                            "Best Regards,\n"+
                            "Sth coop.");

        }



    }
}
