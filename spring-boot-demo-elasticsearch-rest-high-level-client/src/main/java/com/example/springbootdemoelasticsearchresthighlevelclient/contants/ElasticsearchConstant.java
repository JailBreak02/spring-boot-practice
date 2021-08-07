package com.example.springbootdemoelasticsearchresthighlevelclient.contants;

/**
 * ElasticsearchConstant
 *
 * @author BigBoss
 * @version v1.0
 * @since 20200810
 */
public interface ElasticsearchConstant {

    /**
     * 索引名称
     */
    String INDEX_NAME = "person";

    /**
     * 聚合求和名称
     */
    String SUM_NAME = "fee_sum";

    /**
     * 自定义分组桶名称
     */
    String TERMS_NAME = "country_terms";

    /**
     * 聚合平均值名称
     */
    String AVG_NAME = "fee_avg";

}
