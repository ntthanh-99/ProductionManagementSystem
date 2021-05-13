package com.tienthanh;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.tienthanh.config.SecurityConfig;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.security.EmployeeRole;
import com.tienthanh.domain.security.Role;
import com.tienthanh.service.EmployeeService;

@SpringBootApplication
public class ProductionManagementSystemApplication implements CommandLineRunner{
	@Autowired
	private EmployeeService employeeService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductionManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setUsername("admin");
		employee.setPassword(SecurityConfig.passwordEncoder().encode("admin"));
		employee.setPosition("admin");
		employee.setEnabled(true);
		
		Role role = new Role();
		role.setId(0);
		role.setName("EMPLOYEE_ADMIN");
		
		Set<EmployeeRole> employeeRoles= new HashSet<EmployeeRole>();
		employeeRoles.add(new EmployeeRole(role, employee));
		
		employeeService.createEmployee(employee, employeeRoles);
		
	}

}
