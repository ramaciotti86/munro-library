package com.munro.munrolibrary.service;

import com.munro.munrolibrary.bean.CsvLoadBean;
import com.munro.munrolibrary.model.Munro;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MunroService {

    public MunroService() {
    }

    public List<Munro> getMunroListByFilter(String category) {
        List<Munro> allMunros = new ArrayList<>(CsvLoadBean.originalMunroList);
        List<Munro> filteredMunros = new ArrayList<>();

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
