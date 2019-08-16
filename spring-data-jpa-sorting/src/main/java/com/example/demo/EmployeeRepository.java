package com.example.demo;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findByDept(String deptName, Sort sort);
}