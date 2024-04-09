package com.projects.productservice.services;

import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;

import java.util.List;

public class SelfProductService implements ProductService{

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return null;
    }
}
