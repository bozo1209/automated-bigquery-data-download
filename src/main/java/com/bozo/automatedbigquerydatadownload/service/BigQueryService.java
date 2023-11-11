package com.bozo.automatedbigquerydatadownload.service;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import org.springframework.stereotype.Service;

@Service
public class BigQueryService {

    BigQuery bigQuery;

    public TableResult runQuery(String query) throws InterruptedException {
        return bigQuery
                .query(
                        QueryJobConfiguration
                                .newBuilder(query)
                                .build()
                );
    }
}
