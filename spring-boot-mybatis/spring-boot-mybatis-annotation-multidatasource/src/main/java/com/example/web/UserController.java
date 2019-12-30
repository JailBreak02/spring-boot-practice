package com.example.web;

import com.example.mapper.mysql1.User1Mapper;
import com.example.mapper.mysql2.User2Mapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = this.user1Mapper.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(Long id) {
        User user = this.user2Mapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(@RequestBody User user) {
        this.user2Mapper.insert(user);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user) {
        this.user2Mapper.update(user);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.user1Mapper.delete(id);
    }

}
