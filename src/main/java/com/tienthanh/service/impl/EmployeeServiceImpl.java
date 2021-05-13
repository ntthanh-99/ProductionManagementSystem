package com.tienthanh.service.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.config.SecurityConfig;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.security.EmployeeRole;
import com.tienthanh.repository.EmployeeRepository;
import com.tienthanh.repository.RoleRepository;
import com.tienthanh.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	//private static final Logger LOG = (Logger) LoggerFactory.logger(EmployeeService.class);
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Employee createEmployee(Employee employee, Set<EmployeeRole> employeeRoles) {
		Employee localEmployee = employeeRepository.findByUsername(employee.getUsername());
		if(localEmployee!=null) {
			System.out.println("Employee is Exist! Nothing work be done!");
		}
		else {
			for (EmployeeRole employeeRole : employeeRoles) {
				roleRepository.save(employeeRole.getRole());
			}
			employee.getEmployeeRoles().addAll(employeeRoles);
			localEmployee= employeeRepository.save(employee);
		}
		return localEmployee;
	}

	@Override
	public Employee findByUsername(String username) {
		// TODO Auto-generated method stub
		return employeeRepository.findByUsername(username);
	}

	@Override
	public Employee changePassword(Employee employee, String newPassword) {
		// TODO Auto-generated method stub
		employee.setPassword(SecurityConfig.passwordEncoder().encode(newPassword));
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeRepository.findAll();
	}
	
}
