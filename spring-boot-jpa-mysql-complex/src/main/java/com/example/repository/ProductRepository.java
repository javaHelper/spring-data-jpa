package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	@Query("SELECT p.productCode FROM Product p INNER JOIN p.productline pl")
	List<Product> findProductsAndProductLines(); 
}
