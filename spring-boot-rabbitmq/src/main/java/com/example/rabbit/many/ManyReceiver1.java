package com.example.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "many")
public class ManyReceiver1 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("Receiver 1: "+ message);
    }

}
