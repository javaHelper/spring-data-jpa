package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Product;
import com.example.repository.ProductRepository;

@SpringBootApplication
public class SpringBootJpaMysqlComplexApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMysqlComplexApplication.class, args);
	}
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
//		List<Product> products = productRepository.findProductsAndProductLines();
//		System.out.println(products);
	}

}
