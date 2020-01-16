package com.example.rabbitmq;

import com.example.rabbit.hello.HelloSender;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void A_hello() {
        this.helloSender.send();
    }

}
