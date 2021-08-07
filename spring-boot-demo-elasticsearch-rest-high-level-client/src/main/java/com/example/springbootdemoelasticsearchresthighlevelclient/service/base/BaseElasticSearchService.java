package com.example.springbootdemoelasticsearchresthighlevelclient.service.base;

import cn.hutool.core.bean.BeanUtil;
import com.example.springbootdemoelasticsearchresthighlevelclient.config.ElasticsearchProperties;
import com.sun.org.apache.xpath.internal.objects.XObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author BigBoss
 * @version v1.0
 * @since 2020.08.19
 */
@Slf4j
public abstract class BaseElasticSearchService {

    // TODO:

    @Resource
    protected RestHighLevelClient client;

    @Resource
    private ElasticsearchProperties elasticsearchProperties;

    protected static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();

        // 默认缓冲限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    /**
     * create elasticSearch index (asyc)
     *
     * @param index elasticsearch index
     * @author BigBoss
     */
    protected void createIndexRequest(String index) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(index);
            // Settings for this index
            request.settings(Settings.builder().put("index.number_of_shards", elasticsearchProperties.getIndex().getNumberofShards()).put("index.number_of_replicas", elasticsearchProperties.getIndex().getNumberOfReplicas()));

            CreateIndexResponse createIndexResponse = client.indices().create(request, COMMON_OPTIONS);

            log.info(" whether all of the nodes have acknowledged the request : {}", createIndexResponse.isAcknowledged());
            log.info(" Indicates whether the requisite number of shard copies were started for each shard in the index before timing out :{}", createIndexResponse.isShardsAcknowledged());

        } catch (IOException e) {
            throw new ElasticsearchException("创建索引 {" + index + "} 失败");
        }
    }

    /**
     * delete elasticSearch index
     *
     * @param index elasticSearch index name
     * @author BigBoss
     */
    protected void deleteIndexRequest(String index) {
        // TODO:
        DeleteIndexRequest deleteIndexRequest = buildDeleteIndexRequest(index);

        try {
            client.indices().delete(deleteIndexRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException("删除索引 {" + index + "} 失败");
        }

    }

    /**
     * Build DeleteIndexRequest
     *
     * @param index elasticSearch index name
     * @author BigBoss
     */
    private static DeleteIndexRequest buildDeleteIndexRequest(String index) {
        return new DeleteIndexRequest(index);
    }


    /**
     * build IndexRequest
     *
     * @param index  elasticSearch index name
     * @param id     request object id
     * @param object request object
     * @return {@link org.elasticsearch.action.index.IndexRequest}
     * @author BigBoss
     */
    protected static IndexRequest buildIndexRequest(String index, String id, Object object) {
        return new IndexRequest(index).id(id).source(BeanUtil.beanToMap(object), XContentType.JSON);
    }

    /**
     * exec updateRequest
     *
     * @param index  elasticsearch index name
     * @param id     Document id
     * @param object request object
     * @auther BigBoss
     */
    protected void updateRequest(String index, String id, Object object) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(index, id).doc(BeanUtil.beanToMap(object), XContentType.JSON);
            client.update(updateRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException("更新索引 {" + index + "} 数据 {" + object + "} 失败");
        }
    }

    /**
     * exec deleteRequest
     *
     * @param index elasticSearch index nanme
     * @param id    Document id
     * @author BigBoss
     */
    protected void deleteRequest(String index, String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(index, id);
            client.delete(deleteRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            throw new ElasticsearchException("删除索引 {" + index + "} 数据id {" + id + "} 失败");
        }
    }

    /**
     * search all
     *
     * @param index elasticsearch index name
     * @return {@link SearchResponse}
     * @author BigBoss
     */
    protected SearchResponse search(String index) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        // size 默认值为 10, 超过 10 条数据就会查不出来
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;

        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

    /**
     * condition search
     *
     * @param index elasticsearch index name
     * @return SearchRequest {@link SearchRequest}
     * @author BigBoss
     */
    protected SearchResponse condition(String index) {
        SearchRequest searchRequest = new SearchRequest(index);

        // matchQuery：会将搜索词分词，再与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配上，则可查询到。
        // termQuery：不会对搜索词进行分词处理，而是作为一个整体与目标字段进行匹配，若完全匹配，则可查询到。

        BoolQueryBuilder subBoolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("remark", "Centre")) // Centre-Forward
                .should(QueryBuilders.matchQuery("remark", "Midfield")) // Attacking Midfield
                .should(QueryBuilders.matchQuery("remark", "t Win")) // Right Winger 注: 这条 match 不会匹配到
                .should(QueryBuilders.matchQuery("remark", "Second Striker")); // Second Striker

        // 先 And 然后 Or
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(subBoolQueryBuilder)
                .must(QueryBuilders.rangeQuery("age").gte(18).lte(30))
                .must(QueryBuilders.termQuery("country.keyword", "CN"));

        // 设置条件查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);

        // 字段过滤
        String[] includedFields = new String[]{"id", "age", "name", "country", "remark", "fee"};
        searchSourceBuilder.fetchSource(includedFields, new String[]{});

        // 排序
        searchSourceBuilder.sort("age", SortOrder.DESC);

        // 分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);

        // 超时时间
        searchSourceBuilder.timeout(new TimeValue(200, TimeUnit.MILLISECONDS));

        // 发起查询请求
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * sum aggregation search
     *
     * @param index elasticsearch index
     * @param name  aggregation name
     * @return SearchResponse {@link SearchResponse}
     * @author BigBoss
     */
    protected SearchResponse sum(String index, String name) {
        // 创建一个查询请求，并指定索引名称
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // size 默认值为 10, 超过 10 条数据就会查不出来
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);

        // 构建聚合条件
        SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(name).field("fee");
        searchSourceBuilder.aggregation(sumAggregationBuilder);

        // 发起查询请求
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

    /**
     * bucket doc records
     *
     * @param index      elasticSearch index name
     * @param terms_name terms name
     * @param avg_name   terms name
     * @return SearchResponse {@link SearchResponse}
     * @author BigBoss
     */
    protected SearchResponse bucket(String index, String terms_name, String avg_name) {
        // 创建一个查询请求，并指定索引名称
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // size 默认值为 10, 超过 10 条数据就会查不出来
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);

        // 按照国家分组
        TermsAggregationBuilder termsQueryBuilder = AggregationBuilders.terms(terms_name).field("country.keyword");

        //嵌套子聚合查询
        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg(avg_name).field("fee");
        termsQueryBuilder.subAggregation(avgAggregationBuilder);

        // 发起查询请求
        searchSourceBuilder.aggregation(termsQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

}
