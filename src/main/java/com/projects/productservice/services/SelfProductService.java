package com.projects.productservice.services;

import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Category;
import com.projects.productservice.models.Product;
import com.projects.productservice.repositories.CategoryRepository;
import com.projects.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@Primary
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository,
                       CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException(id, "Product not found");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        // Before saving the product object, save the category object

        Category category = product.getCategory();
        if(category.getId() == null) {
            category = categoryRepository.save(category);
        }
        else {
            category = updateCategory(category);
        }
        product.setCategory(category);
        return productRepository.save(product);
    }
    private Category updateCategory(Category category) {
        Optional<Category> category1 = categoryRepository.findById(category.getId());
        if(!category1.isPresent()) {
            return categoryRepository.save(category);
        }
        if(category.getDescription() != null) {
            category1.get().setDescription(category.getDescription());
        }
        category1.get().setUpdatedAt(new Date());
        return categoryRepository.save(category1.get());
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
