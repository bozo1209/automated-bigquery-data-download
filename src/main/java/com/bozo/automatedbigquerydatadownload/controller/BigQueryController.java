package com.bozo.automatedbigquerydatadownload.controller;

import com.bozo.automatedbigquerydatadownload.service.BigQueryService;
import com.google.cloud.bigquery.TableResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class BigQueryController {

    private BigQueryService bigQueryService;

    @GetMapping("/covid_cases_japan")
    public String covidCasesJapan(Model model){
        TableResult covidCasesJapan;
        try {
            covidCasesJapan = bigQueryService.covidCasesJapan();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("tableHeaders", covidCasesJapan.getSchema().getFields());
        model.addAttribute("tableValues", covidCasesJapan.getValues());
        return "covidCasesJapan";
    }
}
