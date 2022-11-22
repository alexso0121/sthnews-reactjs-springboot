package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.Security.SecurityConfig;
import com.example.news_springbootbackend.controller.AuthController;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;

import static com.amazonaws.services.cloudfront.util.SignerUtils.Protocol.http;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
class StoreserviceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JpaUserservice jpaUserservice;

    @MockBean
    private JpaUserrepository jpaUserrepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SecurityConfig securityConfig;


    /*@Test
    void token() throws Exception {
        MvcResult result =mvc.perform(MockMvcRequestBuilders
                .post("/signup")
                        .with(csrf())
                        .param("action","signup")

                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"duke\",\"password\":\"sd\",,\"email\":\"sd\",\"roles\":\"sd\"}"))
                .andExpect(status().is2xxSuccessful()).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }*/

    @Test
    void checksecurity() throws Exception {
        MvcResult result =mvc.perform(MockMvcRequestBuilders
                                .get("/jpauser/alex")

                )
                .andExpect(status().is4xxClientError()).andReturn();
        assertEquals(401,result.getResponse().getStatus());
    }
}