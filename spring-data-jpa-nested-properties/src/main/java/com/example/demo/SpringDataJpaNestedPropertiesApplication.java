package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.support.Repositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.demo"})
public class SpringDataJpaNestedPropertiesApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaNestedPropertiesApplication.class, args);
	}
	@Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private DeptRepository deptRepo;

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = createEmployees();
        empRepo.saveAll(employees);
        
        System.out.println(" -- finding by dept name IT --");
        List<Employee> list = empRepo.findByDeptName("IT");
        list.stream().forEach(System.out::println);
        
        System.out.println(" -- finding by dept entity Admin --");
        Dept admin = deptRepo.findByName("Admin");
        List<Employee> list2 = empRepo.findByDept(admin);
        list2.stream().forEach(System.out::println);
        
        
        /**
         * Limiting Query Results
         */
        System.out.println(" -- finding the employee with max salary --");
        Employee e = empRepo.findTopByOrderBySalaryDesc();
        System.out.println(e);
        
        System.out.println(" -- finding the employee with min salary --");
        e = empRepo.findTopByOrderBySalaryAsc();
        System.out.println(e);
        
        
        System.out.println(" -- finding the top 3 employees with max salary --");
        List<Employee> list3Top = empRepo.findTop3ByOrderBySalaryDesc();
        list3Top.stream().forEach(System.out::println);
        
        
        System.out.println(" -- finding the top 3 employees with min salary --");
        list = empRepo.findTop3ByOrderBySalaryAsc();
        list.stream().forEach(System.out::println);

        System.out.println(" -- finding the first 2 employees with salary 3000 --");
        list = empRepo.findFirst2BySalary(3000);
        list.stream().forEach(System.out::println);

        System.out.println(" -- finding the top 2 employees with max salary in IT dept --");
        list = empRepo.findFirst2ByDept_NameOrderBySalaryDesc("IT");
        list.stream().forEach(System.out::println);
        
        
        /**
    	 *  Using LIKE expressions with Derived Query Methods
    	 */
        System.out.println(" -- finding employee with name like %ana --");
        List<Employee> like = empRepo.findByNameLike("%ana");
        like.forEach(System.out::println);
        
        System.out.println(" -- finding employee with name like %a_a --");
        list = empRepo.findByNameLike("%a_a");
        list.forEach(System.out::println);
        
        
        System.out.println(" -- finding employee with dept name more than 3 chars --");
        list = empRepo.findByDept_NameLike("___%");
        list.forEach(System.out::println);
        
        
        /**
         * Derived Count Query
         */
        System.out.println(" -- finding the employee count with salary greater or equal to 4000  --");
        long  count = empRepo.countBySalaryGreaterThanEqual(4000);
        System.out.println(count);

        System.out.println(" -- finding the employee count with name ending with 'e'  --");
        count = empRepo.countByNameEndingWith("e");
        System.out.println(count);

        System.out.println(" -- finding the employee count with name like '%a_a' --");
        count = empRepo.countByNameLike("%a_a");
        System.out.println(count);
        
        
        /**
         * Using JPA Named Queries
         */
        System.out.println(" -- finding max salaries in Admin and IT depts  --");
        List<Object[]> listNameQueries = empRepo.findMaxSalariesByDept(Arrays.asList("Admin", "IT"));
        listNameQueries.forEach(arr -> {
            System.out.println(Arrays.toString(arr));
        });
        
        
        /**
         * Native Query
         */
       /* System.out.println(" -- finding max/min of average salaries in Admin and IT depts  --");
        Object[][] maxAvgSalaries = empRepo.findMaxMinAvgSalariesOfDept(Arrays.asList("Admin", "IT"));
        for (Object[] maxAvgSalary : maxAvgSalaries) {
            System.out.println("max avg salary: " + maxAvgSalary[0]);
            System.out.println("min avg salary: " + maxAvgSalary[0]);
        }*/
        
        
        /*for (int i = 1; i <= 6; i++) {
            System.out.printf(" --  finding employees have top %s salaries in IT dept  --%n", i);
            List<Employee> es = empRepo.findByDept_NameTopNSalaries(i, "IT");
            es.forEach(System.out::println);
        }*/
        
	}

	private List<Employee> createEmployees() {
        Dept admin = Dept.create("Admin");
        Dept it = Dept.create("IT");
        Dept sale = Dept.create("Sale");
        return Arrays.asList(
                Employee.create("Diana", admin, 5000),
                Employee.create("Joe", it, 2000),
                Employee.create("Sophia", it, 6000),
                Employee.create("Mike", sale, 4000),
                Employee.create("Rose", it, 3000)
        );
    }
}
