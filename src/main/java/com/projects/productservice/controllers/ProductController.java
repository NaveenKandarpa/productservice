package com.projects.productservice.controllers;

import com.projects.productservice.commons.AuthCommons;
import com.projects.productservice.dtos.Role;
import com.projects.productservice.dtos.UserDto;
import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.exceptions.TokenNotFoundException;
import com.projects.productservice.models.Product;
import com.projects.productservice.services.ProductService;
import org.hibernate.action.spi.Executable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthCommons authCommons;
    public ProductController(ProductService productService, AuthCommons authCommons){
        this.productService = productService;
        this.authCommons =  authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id, @RequestHeader("auth") String token) throws ProductNotFoundException {
//        Product product = productService.getProductById(id);
//        if(product == null) {
//            return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(product, HttpStatus.OK);
        UserDto userDto = authCommons.validateToken(token);

        if(userDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(id);
        if(product == null) {
            return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
        }

        // Role based access
//        for(Role role : userDto.getRoles()) {
//            if(role.getValue().equals("ADMIN")) {
//                return new ResponseEntity<>(product, HttpStatus.OK);
//            }
//        }
        return new ResponseEntity<>(product, HttpStatus.FORBIDDEN);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
//        return List.of(new Product(), new Product(), new Product());
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException{
        productService.deleteProduct(id);
//        System.out.println("Product with id: " + id + " has been deleted");
    }
}