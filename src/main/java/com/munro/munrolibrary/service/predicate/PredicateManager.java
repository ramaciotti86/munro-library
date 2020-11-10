package com.munro.munrolibrary.service.predicate;

import com.munro.munrolibrary.model.Munro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class PredicateManager {

    public List<Predicate<Munro>> getCategoryPredicates(String[] categories) {
        List<Predicate<Munro>> predicates = new ArrayList<>();

        Optional.ofNullable(categories).map(x -> {
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

    public List<Predicate<Munro>> getHeightPredicates(Double min, Double max){
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
