package com.bozo.automatedbigquerydatadownload.csv;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CSVGenerator {

    public void writeToCSV(List<String[]> lines, String path) throws IOException {
        ICSVWriter writer = new CSVWriterBuilder(new FileWriter(path)).withSeparator(';').build();
        writer.writeAll(lines);
        writer.close();
    }
}
