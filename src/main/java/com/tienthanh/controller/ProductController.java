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
import com.tienthanh.domain.Product;
import com.tienthanh.domain.security.EmployeeRole;
import com.tienthanh.domain.security.Role;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.ProductService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class ProductController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FormatDateImpl formatDate;
	
	@RequestMapping("/product")
	public String product(Model model, Principal principal) {
		String username = principal.getName();
		Employee employee = employeeService.findByUsername(username);
		model.addAttribute("employee", employee);
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		return "product";
	}
	
	@RequestMapping("/addNewProduct")
	public String addNewProduct(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		model.addAttribute("newProduct", new Product());
		return "newProduct";
	}
	@PostMapping("/addNewProduct")
	public String addNewProductPost(@ModelAttribute("newProduct") Product product) {
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		product.setCreateDate(createDate);
		
		productService.save(product);
		
		return "redirect:/product";
	}
	@RequestMapping("/updateProduct{id}")
	public String update(Model model, Principal principal ,@ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		
		Product updatedProduct = productService.findById(id);
		model.addAttribute("updatedProduct", updatedProduct);
		
		return "updateProduct";
	}
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("updatedProduct") Product product) {
		Product updatedProduct = productService.findById(product.getId());
		updatedProduct.setName(product.getName());
		updatedProduct.setDescription(product.getDescription());
		updatedProduct.setPrice(product.getPrice());
		updatedProduct.setQuanlity(product.getQuanlity());
		
		LocalDateTime localDateTime = LocalDateTime.now();
		Date modifyDate = formatDate.convertLocalDateTimeToDate(localDateTime);
		updatedProduct.setModifyDate(modifyDate);
		productService.save(updatedProduct);
		
		return "redirect:/product";
	}
	
	@RequestMapping("/deleteProduct{id}")
	public String delete(@ModelAttribute("id") Long id) {
		productService.deleleById(id);
		return "redirect:/product";
	}
}
