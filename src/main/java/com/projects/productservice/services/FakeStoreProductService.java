package com.projects.productservice.services;

import com.projects.productservice.dtos.FakeStoreProductDto;
import com.projects.productservice.models.Category;
import com.projects.productservice.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
    @Override
    public Product getProductById(long id) {
        FakeStoreProductDto dto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(dto == null) {
            return null;
        }
        return convertFakeStoreDtoToProduct(dto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] productDtoList;
        productDtoList = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if(productDtoList == null) return null;
        ArrayList<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : productDtoList) {
            products.add(convertFakeStoreDtoToProduct(dto));
        }
        return products;
    }
}