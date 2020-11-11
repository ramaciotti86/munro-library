package com.munro.munrolibrary.service.predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CategoryTestObj {

    String[] categories;
    int expectedResult;

}
