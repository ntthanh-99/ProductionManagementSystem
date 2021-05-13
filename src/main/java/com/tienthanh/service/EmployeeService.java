package com.tienthanh.service;

import java.util.List;
import java.util.Set;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.security.EmployeeRole;

public interface EmployeeService {
	Employee createEmployee(Employee employee, Set<EmployeeRole> employeeRoles);

	Employee findByUsername(String username);
	
	Employee changePassword(Employee employee, String newPassword);
	
	List<Employee> findAll();
}
