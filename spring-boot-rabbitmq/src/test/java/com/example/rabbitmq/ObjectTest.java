package com.example.rabbitmq;

import com.example.model.User;
import com.example.rabbit.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {

    @Autowired
    private ObjectSender objectSender;

    @Test
    public void sendObject() {
        User user = new User();
        user.setName("Big Boss");
        user.setPass("La Li Lu Le Lo");
        this.objectSender.send(user);
    }

}
