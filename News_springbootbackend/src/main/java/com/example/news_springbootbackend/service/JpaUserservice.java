package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.Email.Emailsender;
import com.example.news_springbootbackend.entity.JpaUser;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

//logic for handling the user

@Service
public class JpaUserservice {
    private final JwtEncoder encoder;

    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    public JpaUserservice(JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder) {
        this.encoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private JpaUserrepository jpaUserrepository;

    private Authentication auth;

    @Autowired
    private Emailsender emailsender;


    //method for generating the token after authentication(login)
    public String generateToken (Authentication authentication){
        Instant now=Instant.now();
        String scope=authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("scope",scope).build();
        //return the encoded jwt token
                return this.encoder.encode(JwtEncoderParameters.from((claims))).getTokenValue();
    }

    //signup the jpauser , generate the token and send the email
    public String signup(JpaUser jpaUser) {
        System.out.println(jpaUserrepository.findByUsername(jpaUser.getUsername()));

        //check if the request credential have already existed in the database else save the user
       if(jpaUserrepository.findByUsername(jpaUser.getUsername()).isPresent())
        {return "repeated";}

       //save the user with password encoded
        JpaUser saveuser=jpaUserrepository.save(new JpaUser(jpaUser.getUsername(),
                passwordEncoder.encode(jpaUser.getPassword()),jpaUser.getEmail(),jpaUser.getRoles()));

       //authenticate the user
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jpaUser.getUsername(),jpaUser.getPassword()));

        //generating the token for new register
        String token=generateToken(authentication);

        //send the email

        emailsender.SendEmail(jpaUser.getEmail(),
                "Successful Registration for STHNews ",
                "Dear "+ jpaUser.getUsername()+":"+"\n"+"\n"+
        "Congratulations!We are glad to notice that your have successfully registered to STH NEWS"+"\n"
        +"Our website is a website for reading updated news and weathering forecast." +
                        "Multiple function like store your own news," +
                "watch for history can also be used after you have register.Feel free to contact us with this email\n \n"+
                "Best Regards,\n"+
                "Sth coop.");
        //return the token
       return token;

    }

    //showing the client info with hided password
    public JpaUser getUserByname(String username){
        JpaUser temp=jpaUserrepository.findByUsername(username).orElse(null);
        temp.setPassword(null);
        return temp;
    }
   /* public User updateUser(User user){
        if(repository.checkname(user.getName())!=null){
            User emptyuser=new User();
            return emptyuser;
        }
        User existinguser= repository.findByName(user.getName()).orElse(null);
        existinguser.setName(user.getName());
        existinguser.setEmail(user.getEmail());
        existinguser.setPassword(user.getPassword());
        return repository.save(existinguser);
    }*/
}