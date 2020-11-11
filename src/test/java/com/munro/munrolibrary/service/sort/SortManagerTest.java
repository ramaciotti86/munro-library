package com.munro.munrolibrary.service.sort;

import com.munro.munrolibrary.model.Munro;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.collections4.ComparatorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
public class SortManagerTest {

    @Autowired
    private SortManager sortManager;

    @Test
    void getComparators(){
        //Given
        String[] sort = new String[]{"name,ASC", "height,DESC"};

        //When
        List<Comparator<Munro>> comparators = sortManager.getComparators(sort);

        //Then
        Assertions.assertEquals(comparators.size(), 2);
    }
}
