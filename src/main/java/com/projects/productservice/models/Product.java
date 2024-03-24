package com.projects.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String title;
    private int price;
    private String description;
    private String image;
    private Category category;
}
