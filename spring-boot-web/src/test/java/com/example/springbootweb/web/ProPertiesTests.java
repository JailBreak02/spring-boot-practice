package com.example.springbootweb.web;

import com.example.springbootweb.util.NeoProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProPertiesTests {

    @Autowired
    private NeoProperties neoProperties;

    @Test
    public void getHello() {
        System.out.println(neoProperties.getTitle());
        Assert.assertEquals(neoProperties.getTitle(), "纯洁的微笑");
        Assert.assertEquals(neoProperties.getDescription(), "分享生活和技术");
    }

    //@Test
    public void testMap() {
        Map<String, Long> orderMinTime = new HashMap<String, Long>();
        long xx = orderMinTime.get("123");
    }

}
