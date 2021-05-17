package com.tienthanh.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienthanh.config.SecurityConfig;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.security.EmployeeRole;
import com.tienthanh.domain.security.Role;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.RoleService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class AccountController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private FormatDateImpl formatDate;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String username = principal.getName();
		Employee employee = employeeService.findByUsername(username);
		model.addAttribute("employee", employee);
		List<Employee> employeeList = employeeService.findAll();
		model.addAttribute("employeeList", employeeList);
		return "account";
	}
	
	@RequestMapping("/addNewAccount")
	public String addNewAccount(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		model.addAttribute("newEmployee", new Employee());
		return "newAccount";
	}
	@PostMapping("/addNewAccount")
	public String addNewAccountPost(@ModelAttribute("newEmployee") Employee employee) {
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		
		employee.setPassword(SecurityConfig.passwordEncoder().encode(employee.getPassword()));
		employee.setEnabled(true);
		employee.setCreateDate(createDate);
		
		Role role= roleService.findByName("EMPLOYEE_"+employee.getPosition().toUpperCase());
		if(role==null) {
			role=new Role();
			role.setName("EMPLOYEE_"+employee.getPosition().toUpperCase());
		}
		Set<EmployeeRole> employeeRoles= new HashSet<EmployeeRole>();
		employeeRoles.add(new EmployeeRole(role, employee));
		
		employeeService.createEmployee(employee, employeeRoles);
		return "redirect:/account";
	}
	@RequestMapping("/updateAccount{id}")
	public String update(Model model, Principal principal ,@ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		
		Employee updateEmployee = employeeService.findById(id);
		updateEmployee.setPassword("*****");
		model.addAttribute("updateEmployee", updateEmployee);
		return "updateAccount";
	}
	@PostMapping("/updateAccount")
	public String updateAccount(@ModelAttribute("updateEmployee") Employee employee) {
		Employee updateAccount = employeeService.findById(employee.getId());
		updateAccount.setUsername(employee.getUsername());
		updateAccount.setPassword(SecurityConfig.passwordEncoder().encode(employee.getPassword()));
		updateAccount.setPosition(employee.getPosition());
		//thay doi quyền truy cập
		
		LocalDateTime localDateTime = LocalDateTime.now();
		Date modifyDate = formatDate.convertLocalDateTimeToDate(localDateTime);
		updateAccount.setModifyDate(modifyDate);
		employeeService.save(employee);
		
		return "redirect:/account";
	}
	
	
	@RequestMapping("/deleteAccount{id}")
	public String delete(@ModelAttribute("id") Long id) {
		Employee employee = employeeService.findById(id);
		if(employee.isEnabled()==true) {
			employee.setEnabled(false);
		}
		else {
			employee.setEnabled(true);
		}
		employeeService.save(employee);
		return "redirect:/account";
	}
}
