package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByDept(Dept dept);

	List<Employee> findByDeptName(String deptName);
	
	/**
	 * Limiting Query Results
	 */
	Employee findTopByOrderBySalaryDesc();
	
	Employee findTopByOrderBySalaryAsc();

	List<Employee> findTop3ByOrderBySalaryDesc();

	List<Employee> findTop3ByOrderBySalaryAsc();

	List<Employee> findFirst2BySalary(int i);

	List<Employee> findFirst2ByDept_NameOrderBySalaryDesc(String deptName);
	
	/**
	 *  Using LIKE expressions with Derived Query Methods
	 */
	List<Employee> findByNameLike(String likeString);
    List<Employee> findByDept_NameLike(String likeString);
    
    /**
     * Derived Count Query
     */
    long countByDept(String deptName);
    long countBySalaryGreaterThanEqual(int salary);
    long countByNameEndingWith(String endString);
    long countByNameLike(String likeString);
    
    
    public List<Object[]> findMaxSalariesByDept(List<String> deptNames);
    
    /**
     * Native Query
     */
    /*@Query(value = "SELECT max(t_dept.avg_sal), min(t_dept.avg_sal) "
    		+ "FROM ( SELECT dept , avg(salary ) as avg_sal from Employee e WHERE  dept in ('Admin','IT') GROUP BY dept ) as t_dept ", nativeQuery = true)
    public Object[][] findMaxMinAvgSalariesOfDept(List<String> deptNames);*/
    
    
    /**
     * Using named parameters with @Param
     */
    /*@Query("SELECT e FROM Employee e WHERE e.dept = :dept AND "
            + "(SELECT COUNT(DISTINCT e2.salary) FROM Employee e2 "
            + "WHERE e.salary < e2.salary AND e2.dept = :dept) < :topSalNum "
            + "ORDER BY e.salary DESC")
    List<Employee> findByDept_NameTopNSalaries(@Param("topSalNum") long topSalaryNum, @Param("dept") String dept);*/
    
}
