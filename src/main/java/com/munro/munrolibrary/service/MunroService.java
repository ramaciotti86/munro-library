package com.munro.munrolibrary.service;

import com.munro.munrolibrary.model.Munro;
import com.munro.munrolibrary.service.load.CsvFileLoader;
import com.munro.munrolibrary.service.load.ILoader;
import com.munro.munrolibrary.service.predicate.PredicateManager;
import com.munro.munrolibrary.service.sort.SortManager;
import com.munro.munrolibrary.service.validation.MunroValidation;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MunroService {

    private MunroValidation munroValidation;

    private PredicateManager predicateManager;

    private SortManager sortManager;

    private ILoader loader;

    public List<Munro> getMunroListByFilter(String[] categories, Double min, Double max, String[] sort,
                                            Integer limit) {
        //Validation of Min, max params
        munroValidation.validate(min, max);

        List<Munro> allMunros = new ArrayList<>(loader.getMunros());
        List<Predicate<Munro>> categoryPredicates = predicateManager.getCategoryPredicates(categories);
        List<Predicate<Munro>> heightPredicates = predicateManager.getHeightPredicates(min, max);
        List<Comparator<Munro>> comparators = sortManager.getComparators(sort);

        return allMunros.stream()
                .filter(categoryPredicates.stream().reduce(x -> false, Predicate::or))
                .filter(heightPredicates.stream().reduce(x -> true, Predicate::and))
                .sorted(ComparatorUtils.chainedComparator(comparators))
                .limit(limit != null ? limit : allMunros.size())
                .collect(Collectors.toList());
    }
}
