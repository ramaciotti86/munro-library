package com.munro.munrolibrary.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class Munro implements Serializable {

    private String name;
    private Double height;
    private String category;
    private String gridReference;
}
