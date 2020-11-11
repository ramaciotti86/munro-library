package com.munro.munrolibrary.service.predicate;

import com.munro.munrolibrary.model.Munro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
public class PredicateManagerTest {

    @Autowired
    private PredicateManager predicateManager;

    static CategoryTestObj[] categoryTestObjs(){
        return new CategoryTestObj[] {new CategoryTestObj(new String[]{"MUN", "TOP"}, 2),
                new CategoryTestObj(new String[]{"TOP","MUN"}, 2),
                new CategoryTestObj(new String[]{"MUN"}, 1)};
    }

    @ParameterizedTest(name = "{0} should return {1}")
    @MethodSource("categoryTestObjs")
    void getCategoryPredicates(CategoryTestObj categoryTestObj){
        //Given

        //When
        List<Predicate<Munro>> predicates = predicateManager.getCategoryPredicates(categoryTestObj.categories);

        //Then
        Assertions.assertEquals(predicates.size(), categoryTestObj.expectedResult);
        for (int i = 0; i < categoryTestObj.expectedResult; i++) {
            Assertions.assertTrue(predicates.get(i).test(Munro.builder().category(categoryTestObj.categories[i]).build()));
        }
    }

    @Test
    void getCategoryPredicatesNull(){
        //Given
        String[] categories = null;

        //When
        List<Predicate<Munro>> predicates = predicateManager.getCategoryPredicates(categories);

        //Then
        Assertions.assertEquals(predicates.size(), 1);
    }

}
