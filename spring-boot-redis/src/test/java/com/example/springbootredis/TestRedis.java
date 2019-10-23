package com.example.springbootredis;

import com.example.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    @Bean
    public void redisTemplateInit()
    {
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());

        this.redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws InterruptedException {
        User user = new User("aa", "aa123456", "aa@163.com", "aa", "123");

        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        operations.set("BigBoss", user);

        operations.set("Ocelot", user, 1, TimeUnit.SECONDS);

        Thread.sleep(1 * 1000);

        boolean exists = redisTemplate.hasKey("Ocelot");

        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }

        Assert.assertEquals("aa", operations.get("BigBoss").getUserName());
    }

}
