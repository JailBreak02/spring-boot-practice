package com.example.rabbitmq;

import com.example.rabbit.many.ManySender1;
import com.example.rabbit.many.ManySender2;
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
public class ManyTest {

    @Autowired
    private ManySender1 manySender1;

    @Autowired
    private ManySender2 manySender2;

    @Test
    public void A_oneToMany() {
        for (int i = 0; i < 100; i++) {
            this.manySender1.send(i);
        }
    }

    @Test
    public void B_manyToMany() {
        for (int i = 0; i < 100; i++) {
            this.manySender1.send(i);
            this.manySender2.send(i);
        }
    }

}
