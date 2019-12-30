package com.example.mapper;

import com.example.enums.UserSexEnum;
import com.example.mapper.mysql2.User2Mapper;
import com.example.model.User;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class User2MapperTest {

    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void A_TestInsert() {
        this.user2Mapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        this.user2Mapper.insert(new User("bb", "b123456", UserSexEnum.WOMAN));
        this.user2Mapper.insert(new User("cc", "c123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, this.user2Mapper.getAll().size());
    }

    @Test
    public void B_testQuery() {
        List<User> users = this.user2Mapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.size());
        }
    }

    @Test
    public void C_testUpdate() {
        User user = this.user2Mapper.getOne((long) 1);
        System.out.println(user.toString());
        user.setNickName("New Por");
        this.user2Mapper.update(user);
        System.out.println(user.toString());
    }


}
