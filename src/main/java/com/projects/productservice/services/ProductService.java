package com.projects.productservice.services;

import com.projects.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public Product replaceProduct(long id, Product product);
}
