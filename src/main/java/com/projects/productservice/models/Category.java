package com.projects.productservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
public class Category {
    private long id;
    private String description;
}
