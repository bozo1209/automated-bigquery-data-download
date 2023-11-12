package com.bozo.automatedbigquerydatadownload.utils;

import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.TableResult;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    public static List<String[]> tableResultsToListOfStringArrays(TableResult tableResult){
        List<String[]> list = new ArrayList<>();
        list.add(
                tableResult
                        .getSchema()
                        .getFields()
                        .stream()
                        .map(Field::getName)
                        .toArray(String[]::new)
        );
        tableResult.getValues().forEach(row -> {
            list.add(
                    row
                            .stream()
                            .map(fieldValue ->
                                    !fieldValue.isNull()
                                    ? fieldValue.getStringValue()
                                    : "")
                            .toArray(String[]::new));
        });
        return list;
    }
}
