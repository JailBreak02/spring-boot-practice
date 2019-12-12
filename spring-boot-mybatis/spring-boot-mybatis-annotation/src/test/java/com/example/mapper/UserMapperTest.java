package com.example.mapper;

import com.example.enums.UserSexEnum;
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
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void A_testInsert() {
        this.userMapper.insert(new User("a1", "a123456", UserSexEnum.MAN));
        this.userMapper.insert(new User("b1", "b123456", UserSexEnum.WOMAN));
        this.userMapper.insert(new User("c1", "c123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, this.userMapper.getAll().size());
    }

    @Test
    public void B_testQuery() {
        List<User> users = this.userMapper.getAll();
        System.out.println(users.toString());
    }

    @Test
    public void C_testUpdate() {
        User user = this.userMapper.getOne(30L);
        System.out.println(user.toString());
        user.setNickName("Big Boss");
        this.userMapper.update(user);

        Assert.assertTrue("Big Boss".equals(this.userMapper.getOne(30l).getNickName()));
    }


}
