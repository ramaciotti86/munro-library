package com.munro.munrolibrary.service.load;

import com.munro.munrolibrary.model.Munro;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileLoader implements ILoader{

    public List<Munro> munros = new ArrayList<>();

    @PostConstruct
    private void init() {
        List<Munro> allMunros = new ArrayList<>();
        ClassPathResource cp = new ClassPathResource("munrotab_v6.2.csv");
        try {
            FileReader fileReader = new FileReader(cp.getFile());
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            String[] record;

            while((record = csvReader.readNext()) != null && !record[0].isEmpty()){
                Munro munro = Munro.builder()
                .name(record[5])
                .height(Double.valueOf(record[9]))
                .category(record[27])
                .gridReference(record[13]).build();
                allMunros.add(munro);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        munros.addAll(allMunros);
    }

    public List<Munro> getMunros(){
        return munros;
    }
}
