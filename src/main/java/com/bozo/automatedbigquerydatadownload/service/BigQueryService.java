package com.bozo.automatedbigquerydatadownload.service;

import com.bozo.automatedbigquerydatadownload.enums.Query;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;
import com.google.cloud.bigquery.TableResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public TableResult runQuery(String query, Map<String, Object> parameterValueMap) throws InterruptedException{
        return bigQuery.query(addNamedParameters(query, parameterValueMap));
    }

    private QueryJobConfiguration addNamedParameters(String query, Map<String, Object> parameterValueMap){
        QueryJobConfiguration.Builder builder = QueryJobConfiguration.newBuilder(query);

        parameterValueMap.forEach((k, v) -> {
            if (v instanceof String){
                System.out.println("congratulation! It is String");
                builder.addNamedParameter(k, QueryParameterValue.string((String) v));
            } else if (v instanceof String[]) {
                System.out.println("congratulation! It is String[]");
                builder.addNamedParameter(k, QueryParameterValue.array((String[]) v, String.class));
            }
        });


        return builder.build();
    }

    public TableResult covidCasesJapan() throws InterruptedException {
        return runQuery(Query.JAPAN_COVID.getQuery());
    }

    public TableResult covidCasesJapanWithPrefecture(String prefecture) throws InterruptedException{
        return runQuery(Query.JAPAN_COVID_PREFECTURE.getQuery(), Map.of("prefecture_name", prefecture));
    }

    public TableResult covidCasesJapanWithPrefectureList(List<String> prefectures) throws InterruptedException{
        return runQuery(Query.JAPAN_COVID_PREFECTURES.getQuery(), Map.of("prefecture_names", prefectures.toArray(String[]::new)));
    }
}
