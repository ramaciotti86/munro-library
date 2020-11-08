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
    /*Running
    No
    DoBIH Number
    Streetmap
    Geograph
    Hill-bagging
    Name
    SMC Section
    RHB Section
    _Section
    Height (m)
    Height (ft)
    Map 1:50
    Map 1:25
    Grid Ref
    GridRefXY
    xcoord
    ycoord
    1891
    1921
    1933
    1953
    1969
    1974
    1981
    1984
    1990
    1997
    Post 1997
    Comments*/


}
