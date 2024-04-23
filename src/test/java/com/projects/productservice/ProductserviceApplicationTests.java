package com.projects.productservice;

import com.projects.productservice.models.Product;
import com.projects.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductserviceApplicationTests {
	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}
//	@Test
//	@Transactional
//	public void testTC() {
//		Product product = productRepository.someRandomQuery(1L);

//		System.out.print("Debugging the code");
//	}
}
