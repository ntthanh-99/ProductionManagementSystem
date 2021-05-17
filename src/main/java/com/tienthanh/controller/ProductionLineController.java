package com.tienthanh.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.Product;
import com.tienthanh.domain.ProductionLine;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.ProductService;
import com.tienthanh.service.ProductionLineService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class ProductionLineController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductionLineService productionLineService;
	
	@Autowired
	private FormatDateImpl formatDate;
	
	@RequestMapping("/productionLine")
	public String product(Model model, Principal principal) {
		String username = principal.getName();
		Employee employee = employeeService.findByUsername(username);
		model.addAttribute("employee", employee);
		List<ProductionLine> productionLineList = productionLineService.findAll();
		model.addAttribute("productionLineList", productionLineList);
		return "productionLine";
	}
	
	@RequestMapping("/addNewProductionLine")
	public String addNewProductionLine(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		model.addAttribute("newProductionLine", new ProductionLine());
		return "newProductionLine";
	}
	@PostMapping("/addNewProductionLine")
	public String addNewProductionLinePost(@ModelAttribute("newProductionLine") ProductionLine productionLine) {
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		productionLine.setCreateDate(createDate);
		
		productionLineService.save(productionLine);
		
		return "redirect:/productionLine";
	}
	@RequestMapping("/updateProductionLine{id}")
	public String update(Model model, Principal principal ,@ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		
		ProductionLine updatedProductionLine = productionLineService.findById(id);
		model.addAttribute("updatedProductionLine", updatedProductionLine);
		
		return "updateProductionLine";
	}
	@PostMapping("/updateProductionLine")
	public String updateProduct(@ModelAttribute("updatedProductionLine") ProductionLine productionLine) {
		ProductionLine updatedProductionLine = productionLineService.findById(productionLine.getId());
		updatedProductionLine.setName(productionLine.getName());
		updatedProductionLine.setDescription(productionLine.getDescription());
		updatedProductionLine.setStatus(productionLine.getStatus());
		
		LocalDateTime localDateTime = LocalDateTime.now();
		Date modifyDate = formatDate.convertLocalDateTimeToDate(localDateTime);
		updatedProductionLine.setModifyDate(modifyDate);
		productionLineService.save(updatedProductionLine);
		
		return "redirect:/productionLine";
	}
	
	@RequestMapping("/deleteProductionLine{id}")
	public String delete(@ModelAttribute("id") Long id) {
		productionLineService.deleteById(id);
		return "redirect:/productionLine";
	}
}
