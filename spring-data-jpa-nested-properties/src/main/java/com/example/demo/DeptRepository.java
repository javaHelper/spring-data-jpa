package com.example.demo;
import org.springframework.data.repository.CrudRepository;

public interface DeptRepository extends CrudRepository<Dept, Integer> {
    Dept findByName(String name);
}