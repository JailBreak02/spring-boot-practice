package com.example.springbootdemomqkafka;

import com.example.springbootdemomqkafka.constants.KafkaConsts;
import com.example.springbootdemomqkafka.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SpringBootDemoMqKafkaApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void contextLoads() {
    }

    /**
     * 测试发送消息
     */
    @Test
    public void testSend() throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "Hello,kafka...");

        // 添加回调函数进行消息发送成功的确认以及异常的处理
        SendResult<String, String> sendResult = future.get();
        future.addCallback(result -> log.info("生产者成功发送消息到 Topic：{}, Partition:{}, Offset: {}, Value: {}",
                sendResult.getRecordMetadata().topic(), sendResult.getRecordMetadata().partition(), sendResult.getRecordMetadata().offset(), sendResult.getProducerRecord().value()),
                ex -> log.error("生产者发送消息失败, 原因: {}", ex.getMessage()));

    }

}
