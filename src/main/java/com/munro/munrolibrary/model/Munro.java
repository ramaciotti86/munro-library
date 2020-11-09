package com.munro.munrolibrary.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Munro implements Serializable {

    private String name;
    private Double height;
    private String category;
    private String gridReference;
}
