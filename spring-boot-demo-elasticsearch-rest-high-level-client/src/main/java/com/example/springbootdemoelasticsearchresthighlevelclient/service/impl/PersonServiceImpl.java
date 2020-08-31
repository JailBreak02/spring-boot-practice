package com.example.springbootdemoelasticsearchresthighlevelclient.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.springbootdemoelasticsearchresthighlevelclient.model.Person;
import com.example.springbootdemoelasticsearchresthighlevelclient.service.PersonService;
import com.example.springbootdemoelasticsearchresthighlevelclient.service.base.BaseElasticSearchService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author BigBoss
 * @version v1.0
 * @since 2020.08.21
 */
@Service
public class PersonServiceImpl extends BaseElasticSearchService implements PersonService {


    @Override
    public void createIndex(String index) {
        createIndexRequest(index);
    }

    @Override
    public void deleteIndex(String index) {
        deleteIndexRequest(index);
    }

    @Override
    public void insert(String index, List<Person> list) {

        try {
            list.forEach(person -> {
                IndexRequest request = buildIndexRequest(index, String.valueOf(person.getId()), person);

                try {
                    client.index(request, COMMON_OPTIONS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String index, List<Person> list) {

        list.forEach(person ->{
            updateRequest(index, String.valueOf(person.getId()), person);
        });

    }

    @Override
    public void delete(String index, Person person) {
        if (ObjectUtils.isEmpty(person)) {
            searchList(index).forEach(p -> {
                deleteRequest(index, String.valueOf(p.getId()));
            });
        }
        deleteRequest(index, String.valueOf(person.getId()));
    }

    @Override
    public List<Person> searchList(String index) {

        SearchResponse searchResponse = search(index);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Person> personList = new ArrayList<>();
        Arrays.stream(hits).forEach(documentFields -> {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Person person = BeanUtil.mapToBean(sourceAsMap, Person.class, true);
            personList.add(person);
        });

        return personList;
    }
}
