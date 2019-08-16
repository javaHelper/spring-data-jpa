package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class SpringDataJpaSortingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaSortingApplication.class, args);
	}

	@Autowired
	private EmployeeRepository repo;

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = createEmployees();
		repo.saveAll(employees);

		System.out.println(" -- finding all employees --");
		Iterable<Employee> all = repo.findAll();
		all.forEach(System.out::println);

		System.out.println(" -->>>>>> finding by dept Sales sort by 'salary' and 'name'  --");
		List<Employee> list = repo.findByDept("Sales", Sort.by("salary", "name").ascending());
		list.forEach(System.out::println);
	}

	private List<Employee> createEmployees() {
		return Arrays.asList(Employee.create("Diana", "Sales", 2000), 
				Employee.create("Mike", "Sales", 1000),
				Employee.create("Rose", "IT", 4000), 
				Employee.create("Sara", "Sales", 3000),
				Employee.create("Andy", "Sales", 3000), 
				Employee.create("Charlie", "IT", 2500));
	}
}
