package com.example.rabbitmq;

import com.example.rabbit.topic.TopicSender;
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
public class TopicTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void topic() {
        this.topicSender.send();
    }

    @Test
    public void topic1() {
        this.topicSender.send1();
    }

    @Test
    public void topic2() {
        this.topicSender.send2();
    }

}
