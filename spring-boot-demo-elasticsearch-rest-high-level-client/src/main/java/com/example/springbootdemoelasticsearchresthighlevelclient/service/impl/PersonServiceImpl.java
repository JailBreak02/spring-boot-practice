package com.example.springbootdemoelasticsearchresthighlevelclient.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.springbootdemoelasticsearchresthighlevelclient.model.Person;
import com.example.springbootdemoelasticsearchresthighlevelclient.service.PersonService;
import com.example.springbootdemoelasticsearchresthighlevelclient.service.base.BaseElasticSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

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
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(String index, List<Person> list) {

        list.forEach(person -> {
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

    @Override
    public List<Person> conditionQuery(String index) {

        SearchResponse searchResponse = condition(index);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Person> personList = new ArrayList<>();
        Arrays.stream(hits).forEach(documentFields -> {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Person person = BeanUtil.mapToBean(sourceAsMap, Person.class, true);
            personList.add(person);
        });

        return personList;
    }

    @Override
    public String sumAggregation(String index, String name) {

        SearchResponse searchResponse = sum(index, name);
        // 获取聚合的结果
        Aggregations aggregations = searchResponse.getAggregations();

        // 打印获取的结果
        System.out.println(JSONUtil.toJsonPrettyStr(aggregations));
        System.out.println(JSONUtil.toJsonPrettyStr(aggregations.getAsMap().get(name)));

        String result = JSONUtil.toJsonPrettyStr(aggregations.getAsMap().get(name));

        return result;
    }

    @Override
    public String bucketQuery(String index, String terms_name, String avg_name) {

        String content = null;

        SearchResponse searchResponse = bucket(index, terms_name, avg_name);
        // 获取聚合的结果
        Aggregations aggregations = searchResponse.getAggregations();
        Aggregation aggregation = aggregations.get(terms_name);
        // 打印获取的结果
        System.out.println(JSONUtil.toJsonPrettyStr(aggregation));

        // 获取桶聚合结果
        List<? extends Terms.Bucket> buckets = ((Terms) aggregation).getBuckets();

        StringBuilder stringBuilder = new StringBuilder();

        // 循环遍历各个桶结果
        for (Terms.Bucket bucket : buckets) {
            // 分组的key
            String key = bucket.getKeyAsString();
            Aggregations subAggregations = bucket.getAggregations();
            Aggregation avgAggregations = subAggregations.get(avg_name);

            // 打印获取的结果
            content = JSONUtil.toJsonPrettyStr(avgAggregations);
            System.out.println(content);
            stringBuilder.append(content);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> map = objectMapper.readValue(content, HashMap.class);

                String value = null != map.get("value") ? map.get("value").toString() : "";
                System.out.println(key + "------>" + value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

}
