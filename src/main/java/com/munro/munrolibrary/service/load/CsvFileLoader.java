package com.munro.munrolibrary.service.load;

import com.munro.munrolibrary.model.Munro;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileLoader implements ILoader{

    public List<Munro> munros = new ArrayList<>();

    @PostConstruct
    private void init() {
        List<Munro> allMunros = new ArrayList<>();
        URL resource = CsvFileLoader.class.getClassLoader().getResource("munrotab_v6.2.csv");
        try {
            File file = new File(resource.toURI());
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            String[] record;

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
        munros.addAll(allMunros);
    }

    public List<Munro> getMunros(){
        return munros;
    }
}
