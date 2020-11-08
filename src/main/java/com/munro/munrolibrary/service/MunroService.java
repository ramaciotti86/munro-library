package com.munro.munrolibrary.service;

import com.munro.munrolibrary.config.CsvLoadConfig;
import com.munro.munrolibrary.model.Munro;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MunroService {

    public MunroService() {
    }

    public List<Munro> getMunroListByFilter(String category) {
        List<Munro> allMunros = new ArrayList<>(CsvLoadConfig.originalMunroList);
        List<Munro> filteredMunros = null;

        if(StringUtils.isEmpty(category)){
            filteredMunros.addAll(allMunros.stream()
                    .filter(m -> !m.getCategory().equals(category)).collect(Collectors.toList()));
        }else {
            filteredMunros.addAll(allMunros.stream()
                    .filter(m -> m.getCategory().equals(category)).collect(Collectors.toList()));
        }
        return filteredMunros;
    }
}
