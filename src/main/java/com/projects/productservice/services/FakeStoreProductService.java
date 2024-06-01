package com.projects.productservice.services;

import com.projects.productservice.dtos.FakeStoreProductDto;
import com.projects.productservice.dtos.ProductNotFoundExceptionDto;
import com.projects.productservice.exceptions.ProductNotFoundException;
import com.projects.productservice.models.Category;
import com.projects.productservice.models.Product;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;
    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
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
    public Product getProductById(Long id) throws ProductNotFoundException{
//        int x = 1/0;
//        throw new RuntimeException("Something went wrong in service layer");
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS",
                "PRODUCTS_" + id);

        if(product != null) {
            // Cache hit
            return product;
        }

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException(id, "Product with id: " + id + " not found");
        }

        product = convertFakeStoreDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, product);
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
//        FakeStoreProductDto[] productDtoList;
//        productDtoList = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
//        if(productDtoList == null) return null;
//        ArrayList<Product> products = new ArrayList<>();
//        for(FakeStoreProductDto dto : productDtoList) {
//            products.add(convertFakeStoreDtoToProduct(dto));
//        }
        return null;
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        // This replaces the entire object and sets default value if some attributes are not set
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setImage(product.getImage());
        restTemplate.put("https://fakestoreapi.com/products/" + id, fakeStoreProductDto);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }


}