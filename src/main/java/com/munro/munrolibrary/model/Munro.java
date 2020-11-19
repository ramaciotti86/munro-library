package com.munro.munrolibrary.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
@EqualsAndHashCode
public class Munro implements Serializable {

    private String name;
    private Double height;
    private String category;
    private String gridReference;
}
