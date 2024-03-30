package com.projects.productservice.controllers;

import com.projects.productservice.models.Product;
import com.projects.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
}
