package com.example.template;

import com.example.SpringBootDemoElasticsearchApplicationTests;
import com.example.model.Person;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * <p>
 *     测试 ElasticTemplate 的创建/删除
 * </p>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TemplateTest extends SpringBootDemoElasticsearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    /**
     * 测试 ElasticTemplate 创建 index
     */
    @Test
    public void testCreateIndex() {

        // 创建索引，会根据Item类的@Document朱姐信息来创建
        esTemplate.createIndex(Person.class);

        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        esTemplate.putMapping(Person.class);
    }

    /**
     * 测试 ElasticTemplate 删除 index
     */
    @Test
    public void testDeleteIndex() {
        esTemplate.deleteIndex(Person.class);
    }

}
