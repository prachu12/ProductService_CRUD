package com.demo.productcatlog.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private Category category;
    private String image;
    private double price;


}
