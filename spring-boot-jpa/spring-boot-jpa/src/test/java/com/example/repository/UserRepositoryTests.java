package com.example.repository;

import com.example.model.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void A_testSave() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa", "aa123456", "aa@126.com", "aa", formattedDate));
        userRepository.save(new User("bb", "bb123456", "bb@126.com", "bb", formattedDate));
        userRepository.save(new User("cc", "cc123456", "cc@126.com", "cc", formattedDate));

    }

    @Test
    public void B_testBaseQuery() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        User user = new User("ff", "ff123456", "ff@126.com", "ff", formattedDate);

        Object object = null;

        object = userRepository.findAll();
        object = userRepository.findById(3L);
        object = userRepository.save(user);
        user.setId(2L);
        userRepository.delete(user);
        object = userRepository.count();
        object = userRepository.existsById(3L);
    }

    @Test
    public void D_testCustomSql() {

        Object object = null;

        object = userRepository.modifyById("neo", 3L);
        userRepository.deleteById(3L);
        object = userRepository.findByEmail("ff@126.com");
    }

    @Test
    public void C_testPageQuery() {
        int page = 1, size = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Object object = null;

        object = userRepository.findAll(pageable);
        object = userRepository.findByNickName("aa", pageable);
    }


}
