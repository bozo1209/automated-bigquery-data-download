package com.bozo.automatedbigquerydatadownload.enums;

public enum Query {
    JAPAN_COVID("SELECT * FROM `bigquery-public-data.covid19_public_forecasts.japan_prefecture_28d` LIMIT 10;"),
    JAPAN_COVID_PREFECTURE("SELECT * FROM `bigquery-public-data.covid19_public_forecasts.japan_prefecture_28d` WHERE prefecture_name = @prefecture_name LIMIT 10;"),
    JAPAN_COVID_PREFECTURES("SELECT * FROM `bigquery-public-data.covid19_public_forecasts.japan_prefecture_28d` WHERE prefecture_name IN UNNEST(@prefecture_names) LIMIT 10;");

    private final String query;

    Query(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }
}
