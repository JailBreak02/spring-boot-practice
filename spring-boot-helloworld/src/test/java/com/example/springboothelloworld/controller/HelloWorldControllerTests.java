package com.example.springboothelloworld.controller;

import com.example.springboothelloworld.Model.ConfigProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class HelloWorldControllerTests {

    // 通过注入而不是new的方式才能在Controller里面通过@value获取到配置的数据
    @Autowired
    private MockMvc mvc;

    // 通过注入而不是new的方式才能在Controller里面通过@value获取到配置的数据
    @Autowired
    private HelloWorldController helloWorldController;

    @Before
    public void Setup() {
        // mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
        // 通过注入而不是new的方式才能在Controller里面通过@value获取到配置的数据
        mvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
