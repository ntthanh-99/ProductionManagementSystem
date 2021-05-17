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
import com.tienthanh.domain.Material;
import com.tienthanh.domain.Product;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.MaterialService;
import com.tienthanh.service.ProductService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class MaterialController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private FormatDateImpl formatDate;

	@RequestMapping("/material")
	public String material(Model model, Principal principal) {
		String username = principal.getName();
		Employee employee = employeeService.findByUsername(username);
		model.addAttribute("employee", employee);
		List<Material> materialList = materialService.findAll();
		model.addAttribute("materialList", materialList);
		return "material";
	}

	@RequestMapping("/addNewMaterial")
	public String addNewMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		model.addAttribute("newMaterial", new Material());
		return "newMaterial";
	}

	@PostMapping("/addNewMaterial")
	public String addNewMaterialPost(@ModelAttribute("newMaterial") Material material) {
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		material.setCreateDate(createDate);

		materialService.save(material);

		return "redirect:/material";
	}

	@RequestMapping("/updateMaterial{id}")
	public String update(Model model, Principal principal, @ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		Material updatedMaterial = materialService.findById(id);
		model.addAttribute("updatedMaterial", updatedMaterial);

		return "updateMaterial";
	}

	@PostMapping("/updateMaterial")
	public String updateMaterial(@ModelAttribute("updatedMaterial") Material material) {
		Material updatedMaterial = materialService.findById(material.getId());
		updatedMaterial.setName(material.getName());
		updatedMaterial.setQuanlity(material.getQuanlity());
		updatedMaterial.setPriceImport(material.getPriceImport());

		LocalDateTime localDateTime = LocalDateTime.now();
		Date modifyDate = formatDate.convertLocalDateTimeToDate(localDateTime);
		updatedMaterial.setModifyDate(modifyDate);

		materialService.save(updatedMaterial);

		return "redirect:/material";
	}

	@RequestMapping("/deleteMaterial{id}")
	public String delete(@ModelAttribute("id") Long id) {
		materialService.deleteById(id);
		return "redirect:/material";
	}
}
