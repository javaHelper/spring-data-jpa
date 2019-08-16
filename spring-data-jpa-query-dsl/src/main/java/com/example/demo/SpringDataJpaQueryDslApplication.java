package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootApplication
public class SpringDataJpaQueryDslApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaQueryDslApplication.class, args);
	}
	
	@Autowired
    private EmployeeRepository repo;

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = createEmployees();
        repo.saveAll(employees);
        System.out.println("-- employees persisted --");
        employees.forEach(System.out::println);

        
        System.out.println(" -- employees having salary greater than 3000 order by salary --");
        BooleanExpression booleanExpression = QEmployee.employee.salary.goe(3000);
        OrderSpecifier<Integer> orderSpecifier = QEmployee.employee.salary.asc();
        
        Iterable<Employee> empIterable = repo.findAll(booleanExpression, orderSpecifier);
        empIterable.forEach(System.out::println);
        
        
        System.out.println(" -- employees in IT and Admin depts and salary between 3000 and 5000 --");
        BooleanExpression expression = QEmployee.employee.dept.in("IT", "Admin");
        BooleanExpression between = QEmployee.employee.salary.between(3000, 5000);
        
        Iterable<Employee> employee3 = repo.findAll(expression.and(between));
        employee3.forEach(System.out::println);
        
        
        System.out.println(" -- find employee Mike --");
        Optional<Employee> opt = repo.findOne(QEmployee.employee.name.eq("Mike"));
        System.out.println(opt.get());
	}

	private List<Employee> createEmployees() {
        return Arrays.asList(
                Employee.create("Diana", "Admin", 2000),
                Employee.create("Mike", "Sales", 1000),
                Employee.create("Rose", "IT", 4000),
                Employee.create("Sara", "Admin", 3500),
                Employee.create("Randy", "Sales", 3000),
                Employee.create("Charlie", "IT", 2500)
        );
    }
}
