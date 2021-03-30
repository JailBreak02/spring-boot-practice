package com.example.springbootdemomqkafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author BigBoss
 * @version v1.0
 * @since 2021.03.30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    /**
     * 用户编号
     * */
    private String customerNo;

    /**
     * 年龄
     * */
    private Integer age;

    /**
     * 生日
     * */
    private Date birthday;

    /**
     * 工资
     * */
    private Double Salary;

}