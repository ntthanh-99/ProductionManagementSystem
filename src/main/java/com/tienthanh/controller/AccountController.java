package com.tienthanh.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienthanh.domain.Employee;
import com.tienthanh.service.EmployeeService;

@Controller
public class AccountController {
	@Autowired
	private EmployeeService employeeService;
	
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
	public String addNewAccountPost(Model model, Principal principal, @ModelAttribute("newEmployee") Employee employee) {
		
		return "redirect:/account";
	}
}
