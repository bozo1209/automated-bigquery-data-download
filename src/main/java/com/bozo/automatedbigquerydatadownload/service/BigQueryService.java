package com.bozo.automatedbigquerydatadownload.service;

import com.bozo.automatedbigquerydatadownload.enums.Query;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@AllArgsConstructor
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

    public TableResult covidCasesJapan() throws InterruptedException {
        return runQuery(Query.JAPAN_COVID.getQuery());
    }

    public TableResult covidCasesJapanWithPrefecture(String prefecture) throws InterruptedException{
        return runQuery(Query.JAPAN_COVID_PREFECTURE.getQuery().replaceAll("\\$\\{prefectureName\\}", prefecture));
    }
}
