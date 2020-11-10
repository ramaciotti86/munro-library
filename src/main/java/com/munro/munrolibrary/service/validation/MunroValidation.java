package com.munro.munrolibrary.service.validation;

import org.springframework.stereotype.Component;

@Component
public class MunroValidation {

    public void validate(Double min, Double max){
        if (min != null && max != null){
            if (max < min){
                throw new RuntimeException("max less than min");
            }
        }
    }
}