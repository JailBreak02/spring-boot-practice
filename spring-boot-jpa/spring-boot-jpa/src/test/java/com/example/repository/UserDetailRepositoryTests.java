package com.example.repository;

import com.example.model.Address;
import com.example.model.UserDetail;
import com.example.model.UserInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDetailRepositoryTests {

    @Resource
    private AddressRepository addressRepository;

    @Resource
    private UserDetailRepository userDetailRepository;

    @Test
    public void A_testSaveAddress() {
        Address address = new Address();
        address.setUserId(1L);
        address.setCity("北京");
        address.setProvince("北京");
        address.setStreet("分钟寺");
        addressRepository.save(address);
    }

    @Test
    public void B_testSaveUserDetail() {
        UserDetail userDetail = new UserDetail();

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userDetail.setUserId(4L);
        userDetail.setHobby("吃鸡游戏");
        userDetail.setAge(28);
        userDetail.setIntroduction("一个爱玩的人");

        userDetailRepository.save(userDetail);
    }

    @Test
    public void C_testUserInfo() {

        List<UserInfo> userInfos = userDetailRepository.findUserInfo("吃鸡游戏");
        for (UserInfo userInfo : userInfos) {
            System.out.println("userInfo: " + userInfo.getUserName() + "-" + userInfo.getEmail() + "-" + userInfo.getHobby() + "-" + userInfo.getIntroduction());
        }
    }

}
