package com.example.repository;

import com.example.model.SQL.User;
import com.example.repository.mysql1.UserMySQL1Repository;
import com.example.repository.mysql2.UserMySQL2Repository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserMySQL1Repository userMySQL1Repository;

    @Resource
    private UserMySQL2Repository userMySQL2Repository;

    @Test
    public void A_TestSave() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        this.userMySQL1Repository.save(new User("aa", "aa123456", "aa@163.com", "aa123", formattedDate));
        this.userMySQL1Repository.save(new User("bb", "bb123456", "bb@163.com", "bb123", formattedDate));
        this.userMySQL2Repository.save(new User("cc", "cc123456", "cc@163.com", "cc123", formattedDate));
    }

    @Test
    public void C_testDelete() {
        this.userMySQL1Repository.deleteAll();
        this.userMySQL2Repository.deleteAll();
    }

    @Test
    public void B_testBaseQuery() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        User user = new User("ff", "ff123456", "ff@126.com", "ff", formattedDate);

        Object object = null;

        object =this.userMySQL1Repository.findAll();
        object =this.userMySQL2Repository.findById(3l);
        object =this.userMySQL2Repository.save(user);
        user.setId(2l);
        this.userMySQL1Repository.delete(user);
        object =this.userMySQL1Repository.count();
        object =this.userMySQL2Repository.findById(3l);
    }

}
