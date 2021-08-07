package com.example.springbootdemoelasticsearchresthighlevelclient.service;

import com.example.springbootdemoelasticsearchresthighlevelclient.model.Person;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * PersonService
 *
 * @author BigBoss
 * @version v1.0
 * @since 2020/08/17
 */
public interface PersonService {

    /**
     * create Index
     *
     * @param index elasticSearch index name
     * @author BigBoss
     */
    void createIndex(String index);

    /**
     * delete Index
     *
     * @param index elasticSearch index name
     * @author BigBoss
     */
    void deleteIndex(String index);

    /**
     * insert document source
     *
     * @param index elasticSearch index name
     * @param list  data source
     * @author BigBoss
     */
    void insert(String index, List<Person> list);

    /**
     * update documen source
     *
     * @param index elasticSearch index name
     * @param list  data source
     * @author BigBoss
     */
    void update(String index, List<Person> list);

    /**
     * delete document source
     *
     * @param index  elasticSearch index name
     * @param person delete data source and allow null object
     */
    void delete(String index, @Nullable Person person);

    /**
     * search all doc records
     *
     * @param index elasticSearch index name
     * @return person List
     * @author BigBoss
     */
    List<Person> searchList(String index);

    /**
     * condition doc records
     *
     * @param index elasticSearch index name
     * @return person list
     * @author BigBoss
     */
    List<Person> conditionQuery(String index);

    /**
     * sum doc records
     *
     * @param index elasticsearch index name
     * @return sum aggregation result
     * @@return person list
     */
    String sumAggregation(String index, String name);

    /**
     * bucket doc records
     *
     * @param index      elasticSearch index name
     * @param terms_name terms name
     * @param avg_name   terms name
     * @return bucket result
     * @author BigBoss
     */
    String bucketQuery(String index, String terms_name, String avg_name);

}
