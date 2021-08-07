package com.example.springbootdemoelasticsearchresthighlevelclient.service;

import cn.hutool.json.JSONUtil;
import com.example.springbootdemoelasticsearchresthighlevelclient.SpringBootDemoElasticsearchRestHighLevelClientApplicationTests;
import com.example.springbootdemoelasticsearchresthighlevelclient.contants.ElasticsearchConstant;
import com.example.springbootdemoelasticsearchresthighlevelclient.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BigBoss
 * @version v1.0
 * @since 2020.08.21
 */
public class PersonServiceTest extends SpringBootDemoElasticsearchRestHighLevelClientApplicationTests {

    @Autowired
    private PersonService personService;

    /**
     * 测试删除索引
     */
    @Test
    public void deleteIndexTest() {
        personService.deleteIndex(ElasticsearchConstant.INDEX_NAME);
    }


    /**
     * 测试创建索引
     */
    @Test
    public void createIndexTest() {
        personService.createIndex(ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 测试新增
     */
    @Test
    public void insertTest() {
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().age(19).birthday(new Date()).country("CN").id(1L).name("Kylian Mbappé").remark("Centre-Forward").fee(159.50).build());
        list.add(Person.builder().age(19).birthday(new Date()).country("CN").id(2L).name("Philippe Coutinho").remark("Attacking Midfield").fee(148.50).build());
        list.add(Person.builder().age(19).birthday(new Date()).country("CN").id(3L).name("Ousmane Dembélé").remark("Right Winger").fee(148.50).build());
        list.add(Person.builder().age(19).birthday(new Date()).country("CN").id(4L).name("João Félix").remark("Second Striker").fee(139.20).build());
        list.add(Person.builder().age(99).birthday(new Date()).country("CN").id(5L).name("Antoine Griezmann").remark("Second Striker").fee(132.00).build());
        list.add(Person.builder().age(28).birthday(new Date()).country("CN").id(6L).name("Cristiano Ronaldo").remark("Centre-Forward").fee(128.70).build());
        list.add(Person.builder().age(28).birthday(new Date()).country("US").id(7L).name("Eden Hazard").remark("Left Winger").fee(126.50).build());
        list.add(Person.builder().age(23).birthday(new Date()).country("US").id(8L).name("Paul Pogba").remark("Central Midfield").fee(115.50).build());
        list.add(Person.builder().age(23).birthday(new Date()).country("US").id(9L).name("Gareth Bale").remark("Right Winger").fee(111.10).build());
        list.add(Person.builder().age(23).birthday(new Date()).country("LAT").id(10L).name("Gonzalo Higuaín").remark("Centre-Forward").fee(99.00).build());
        list.add(Person.builder().age(99).birthday(new Date()).country("LAT").id(11L).name("Luis Suárez").remark("Centre-Forward").fee(89.89).build());
        list.add(Person.builder().age(99).birthday(new Date()).country("LAT").id(12L).name("Neymar").remark("Left Winger").fee(244.20).build());

        personService.insert(ElasticsearchConstant.INDEX_NAME, list);
    }

    /**
     * 测试更新
     */
    @Test
    public void updateTest() {

        Person person = Person.builder().age(33).birthday(new Date()).country("ID_update").id(3L).name("呵呵update").remark("test3_update").build();
        List<Person> list = new ArrayList<>();
        list.add(person);

        personService.update(ElasticsearchConstant.INDEX_NAME, list);
    }

    /**
     * 测试删除
     */
    @Test
    public void deleteTest() {
        personService.delete(ElasticsearchConstant.INDEX_NAME, Person.builder().id(1L).build());
    }

    /**
     * 测试查询
     */
    @Test
    public void searchListTest() {
        List<Person> personList = personService.searchList(ElasticsearchConstant.INDEX_NAME);
        System.out.println(JSONUtil.toJsonPrettyStr(personList));
    }

    /**
     * 条件查询
     */
    @Test
    public void conditionQueryTest() {
        List<Person> personList = personService.conditionQuery(ElasticsearchConstant.INDEX_NAME);
        System.out.println(JSONUtil.toJsonPrettyStr(personList));
    }

    /**
     * 求和聚合查询
     */
    @Test
    public void sumAggregationTest() {
        String result = personService.sumAggregation(ElasticsearchConstant.INDEX_NAME, ElasticsearchConstant.SUM_NAME);
        System.out.println(result);
    }

    /**
     * 桶查询
     */
    @Test
    public void bucketQueryTest() {
        String result = personService.bucketQuery(ElasticsearchConstant.INDEX_NAME, ElasticsearchConstant.TERMS_NAME, ElasticsearchConstant.AVG_NAME);
        System.out.println(result);
    }

}
