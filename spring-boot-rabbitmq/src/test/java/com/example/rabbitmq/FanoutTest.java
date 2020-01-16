package com.example.rabbitmq;

import com.example.rabbit.fanout.FanoutSender;
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
public class FanoutTest {

    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void fanoutSender() {
        this.fanoutSender.send();
    }

}
