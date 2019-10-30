package com.example.repository;

import com.example.model.UserDetail;
import com.example.param.UserDetailParam;
import com.example.service.UserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaSpecificationTests {

    @Resource
    private UserDetailService userDetailService;

    @Test
    public void testFindByCondition() {

        int page = 0, size = 10;

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable pageable = PageRequest.of(page, size, sort);
        UserDetailParam userDetailParam = new UserDetailParam();
        userDetailParam.setIntroduction("一个爱玩的人");
        userDetailParam.setMinAge(10);
        userDetailParam.setMaxAge(30);
        Page<UserDetail> page1 = userDetailService.findByCondition(userDetailParam, pageable);

        for (UserDetail userDetail : page1) {
            System.out.println("userDetail: " + userDetail.toString());
        }
    }

}
