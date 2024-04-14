package com.projects.productservice.repositories;

import com.projects.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    List<Product> findByTitle(String title);

    List<Product> findByTitleContains(String str);

    List<Product> findByTitleAndDescription(String title, String description);

    Optional<Product> findByImage(String url);

    @Override
    Product save(Product product);

    @Override
    void delete(Product product);
}
