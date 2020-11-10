package com.munro.munrolibrary.service.validation;

import com.munro.munrolibrary.exception.MinGreaterThanMaxException;
import org.springframework.stereotype.Component;

@Component
public class MunroValidation {

    public void validate(Double min, Double max){
        if (min != null && max != null){
            if (max < min){
                throw new MinGreaterThanMaxException("Min value is greater then Max value");
            }
        }
    }
}