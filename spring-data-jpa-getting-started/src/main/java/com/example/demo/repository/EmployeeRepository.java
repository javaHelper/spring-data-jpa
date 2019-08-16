package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDept(String dept);
	List<Employee> findBySalaryGreaterThan(int salary);
	List<Employee> findByDeptAndSalaryLessThan(String deptName, int salary);
}