package com.example.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	// Derived Query Methods
	
	List<Customer> findByCountryAndPostalCode(String country, String postalCode);
	
	List<Customer> findByCreditLimitGreaterThan(BigDecimal creditLimit);
	
	List<Customer> findByCustomerNumberLessThan(Integer custNumber);
}
