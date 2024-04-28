package com.projects.productservice.controllers;

import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Product;
import com.projects.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean // This is a mocked object
    private ProductService productService;

    @Test
    void validGetProductByIdTest() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Macbook pro");
        product.setPrice(150000);
        when(productService.getProductById(1L))
                .thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product actualProduct = responseEntity.getBody();
        assertEquals(product, actualProduct);
//        assertThrows(ProductNotFoundException.class, null);
    }
    @Test
    void validGetAllProducts() {
        List<Product> products = List.of(new Product(), new Product(), new Product());
        when(productService.getAllProducts())
                .thenReturn(products);

        List<Product> productList = productController.getAllProducts();
        assertEquals(products, productList);
    }
}
