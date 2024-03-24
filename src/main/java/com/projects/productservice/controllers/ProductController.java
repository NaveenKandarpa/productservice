package com.projects.productservice.controllers;

import com.projects.productservice.models.Product;
import com.projects.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductBtId(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }
}
