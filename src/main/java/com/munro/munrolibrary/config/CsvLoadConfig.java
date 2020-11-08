package com.munro.munrolibrary.config;

import com.munro.munrolibrary.model.Munro;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
public class CsvLoadConfig {

    public static final List<Munro> originalMunroList = getAllMunroList();

    private static List<Munro> getAllMunroList() {
        List<Munro> allMunros = new ArrayList<>();
        URL resource = CsvLoadConfig.class.getClassLoader().getResource("munrotab_v6.2.csv");
        try {
            File file = new File(resource.toURI());
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            String[] record = null;

            while((record = csvReader.readNext()) != null && !record[0].isEmpty()){
                Munro munro = new Munro();
                munro.setName(record[5]);
                munro.setHeight(Double.valueOf(record[9]));
                munro.setCategory(record[27]);
                munro.setGridReference(record[13]);
                allMunros.add(munro);
            }
        } catch (IOException | URISyntaxException | CsvException e) {
            e.printStackTrace();
        }
        return allMunros;
    }
}
