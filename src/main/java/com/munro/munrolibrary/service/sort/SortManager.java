package com.munro.munrolibrary.service.sort;

import com.munro.munrolibrary.model.Munro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class SortManager {

    public List<Comparator<Munro>> getComparators(String[] sort) {
        List<Comparator<Munro>> comparators = new ArrayList<>();

        Optional.ofNullable(sort).map(x -> {
                for (String sortElement : sort) {
                    String[] element = sortElement.split("-");
                    if (element[0].equals("name")) {
                        if (element[1].equals("ASC")) {
                            comparators.add(Comparator.comparing(Munro::getName));
                        } else if (element[1].equals("DESC")) {
                            comparators.add(Comparator.comparing(Munro::getName).reversed());
                        }
                    } else if (element[0].equals("height")) {
                        if (element[1].equals("ASC")) {
                            comparators.add(Comparator.comparing(Munro::getHeight));
                        } else if (element[1].equals("DESC")) {
                            comparators.add(Comparator.comparing(Munro::getHeight).reversed());
                        }
                    }
                }
            return comparators;
        }).orElseGet(() -> {
            comparators.add(Comparator.comparing(Munro::getName));
            return comparators;
        });
        return comparators;
    }

}
