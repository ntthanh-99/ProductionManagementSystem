package com.tienthanh.repository;

import org.springframework.data.repository.CrudRepository;

import com.tienthanh.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	Employee findByUsername(String username);
}
