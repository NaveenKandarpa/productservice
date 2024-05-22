package com.projects.productservice.services;

import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product replaceProduct(long id, Product product);
    public Product createProduct(Product product);
    public void deleteProduct(Long id) throws ProductNotFoundException;
}
