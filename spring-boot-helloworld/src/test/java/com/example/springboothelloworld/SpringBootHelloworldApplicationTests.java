package com.example.springboothelloworld;

import com.example.springboothelloworld.Model.ConfigProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootHelloworldApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("hello world");
    }

}
