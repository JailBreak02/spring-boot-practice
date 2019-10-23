package com.example.web;

import com.example.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@RestController
public class UserController {

    @RequestMapping("/getUser")
    @Cacheable(value = "user-key")
    public User getUser() {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINESE);
        String formattedDate = dateFormat.format(date);

        User user = new User("aa", "aa123456", "aa@126.com", "aa", formattedDate);

        return user;
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);

        return session.getId();
    }

}
