package com.munro.munrolibrary.service;

import com.munro.munrolibrary.bean.CsvLoadBean;
import com.munro.munrolibrary.model.Munro;
import com.munro.munrolibrary.service.validation.MunroValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MunroService {

    private MunroValidation munroValidation;

    public List<Munro> getMunroListByFilter(String[] categories, Double min, Double max, String height, String name,
                                            Integer limit) {
        //Validation of Min, max params
        munroValidation.validate(min, max);

        List<Munro> allMunros = new ArrayList<>(CsvLoadBean.originalMunroList);
        List<Predicate<Munro>> categoryPredicates = getCategoryPredicates(categories);
        List<Predicate<Munro>> heightPredicates = getHeightPredicates(min, max);

        return allMunros.stream()
                .filter(categoryPredicates.stream().reduce(x -> false, Predicate::or))
                .filter(heightPredicates.stream().reduce(x -> true, Predicate::and))
                .limit(limit != null ? limit : allMunros.size())
                .collect(Collectors.toList());
    }

    private List<Predicate<Munro>> getCategoryPredicates(String[] categories) {
        List<Predicate<Munro>> predicates = new ArrayList<>();

        Optional.ofNullable(categories).map(categoriesList -> {
            for (String category : categories) {
                predicates.add(m -> m.getCategory().equals(category));
            }
            return predicates;
        }).orElseGet(() -> {
            predicates.add(Objects::nonNull);
            return predicates;
        });
        return predicates;
    }

    private List<Predicate<Munro>> getHeightPredicates(Double min, Double max){
        List<Predicate<Munro>> predicates = new ArrayList<>();

        //Min Height
        Optional.ofNullable(min).map(predicateList -> {
            predicates.add(m -> m.getHeight() >= min);
            return predicates;
        }).orElseGet(() -> {
            predicates.add(Objects::nonNull);
            return predicates;
        });

        //Max Height
        Optional.ofNullable(max).map(predicateList -> {
            predicates.add(m -> m.getHeight() <= max);
            return predicates;
        }).orElseGet(() -> {
            predicates.add(Objects::nonNull);
            return predicates;
        });

        return predicates;
    }
}
