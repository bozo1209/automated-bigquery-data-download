package com.bozo.automatedbigquerydatadownload.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class BigQueryConfiguration {

    private final String PROJECT_ID = "mydatabasetest-392022";
    private final String KEY_PATH = "keys/gq-key.json";

    public BigQuery getBigQuery(){
        return BigQueryOptions
                .newBuilder()
                .setProjectId(PROJECT_ID)
                .setCredentials(googleCredentials())
                .build()
                .getService();
    }

    private GoogleCredentials googleCredentials(){
        GoogleCredentials googleCredentials = null;

        try(FileInputStream fileInputStream = new FileInputStream(new ClassPathResource(KEY_PATH).getFile().getAbsoluteFile())) {
            googleCredentials = ServiceAccountCredentials.fromStream(fileInputStream);
        } catch (IOException e){
            e.printStackTrace();
        }

        return googleCredentials;
    }
}
