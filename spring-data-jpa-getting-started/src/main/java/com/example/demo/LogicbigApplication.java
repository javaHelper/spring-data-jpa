package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages= {"com.example.demo.repository"})
public class LogicbigApplication implements CommandLineRunner {
	@Autowired
    private EmployeeRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(LogicbigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = createEmployees();
		
		System.out.println(" -- saving employees --");
        System.out.println(employees);
        repo.saveAll(employees);
        
        System.out.println(" -- finding all employees --");
        Iterable<Employee> all = repo.findAll();
        System.out.println(all);
        
        
        System.out.println("-- finding by id --");
        Optional<Employee> employeeOptional = repo.findById(1L);
        Employee employee = employeeOptional.orElseThrow(RuntimeException::new);
        System.out.println(employee);
        
        
        System.out.println(" -- finding by dept --");
        List<Employee> findByDept = repo.findByDept("IT");
        findByDept.stream().forEach(System.out::println);
        
        System.out.println(" -- finding by salary greater than 3000 --");
        List<Employee> list2 = repo.findBySalaryGreaterThan(3000);
        list2.stream().forEach(System.out::println);

        System.out.println(" -- finding by dept=IT and salary less than 4000 --");
        List<Employee> list3 = repo.findByDeptAndSalaryLessThan("IT", 4000);
        list3.stream().forEach(System.out::println);

       /* System.out.println("-- deleting --");
        System.out.println(employee);
        repo.delete(employee);

        
        System.out.println("-- updating --");
        Employee employee2 = repo.findById(2L).orElseThrow(RuntimeException::new);
        System.out.println(employee2);
        employee2.setDept("IT");
        repo.save(employee2);
        
        System.out.println(" -- finding all employees again --");
        Iterable<Employee> all2 = repo.findAll();
        System.out.println(all2);*/
	}

	private List<Employee> createEmployees() {
        return Arrays.asList(
                Employee.create("Diana", "Admin", 5000),
                Employee.create("Joe", "IT", 2000),
                Employee.create("Sophia", "IT", 6000),
                Employee.create("Mike", "Sale", 4000),
                Employee.create("Rose", "IT", 3000)
        );
    }
}
