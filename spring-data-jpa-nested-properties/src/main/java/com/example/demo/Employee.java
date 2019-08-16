package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

// Using named parameters with @Param

@Entity
@NamedQuery(name = "Employee.findMaxSalariesByDept", 
			query = "SELECT e.dept, MAX(e.salary) FROM Employee e GROUP BY e.dept.name HAVING e.dept.name in ?1")
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	private Dept dept;
	private int salary;
	// private String deptName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public static Employee create(String name, Dept dept, int salary) {
		Employee e = new Employee();
		e.setName(name);
		e.setDept(dept);
		// e.deptName= dept.getName();
		e.setSalary(salary);
		return e;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", dept='" + dept + '\'' + ", salary=" + salary
				+ '}';
	}
}