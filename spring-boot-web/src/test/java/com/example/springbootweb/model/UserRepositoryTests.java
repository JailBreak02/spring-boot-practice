package com.example.springbootweb.model;

import com.example.springbootweb.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa@126.com", "aa123456", formattedDate, "aa"));
        userRepository.save(new User("bb2", "bb@126.com", "bb123456", formattedDate, "bb"));
        userRepository.save(new User("cc3", "cc@126.com", "cc123456", formattedDate, "cc"));

        Assert.assertEquals("bb2", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));
    }

}
