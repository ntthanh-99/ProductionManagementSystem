package com.tienthanh.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.Supplier;
import com.tienthanh.repository.SupplierRepository;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.SupplierService;

@Controller
public class ImportMaterialController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SupplierService supplierServie;

	@RequestMapping("/importMaterial")
	public String importMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<Supplier> supplierList = supplierServie.findAll();

		return "importMaterial";
	}
}
