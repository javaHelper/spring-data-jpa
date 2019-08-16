package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaSpelExpressionApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaSpelExpressionApplication.class, args);
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

        System.out.println(" -- finding by dept Admin  --");
        List<Employee> list = repo.findByDepartment("Admin");
        list.forEach(System.out::println);
	}
	
	private List<Employee> createEmployees() {
        return Arrays.asList(
                Employee.create("Diana", "Admin", 2000),
                Employee.create("Mike", "Sale", 1000),
                Employee.create("Rose", "IT", 4000),
                Employee.create("Sara", "Admin", 3500),
                Employee.create("Randy", "Sale", 3000),
                Employee.create("Charlie", "IT", 2500)
        );
    }
}
