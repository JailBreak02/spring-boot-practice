package com.example.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManySender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "spring boot many queue" + "***** " + i;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("many", context);
    }

}
