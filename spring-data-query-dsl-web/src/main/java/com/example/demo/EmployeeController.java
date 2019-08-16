package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;

	@GetMapping("/employees")
	public List<Employee> getEmployees(Predicate predicate) {
		Iterable<Employee> iterable = repository.findAll(predicate);

		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

	@GetMapping("/employeesAsString")
	public String getEmployeesAsString(@QuerydslPredicate(root = Employee.class) Predicate predicate) {
		Iterable<Employee> iterable = repository.findAll(predicate);
		
		List<Employee> employees = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return employees.toString();
	}
}
