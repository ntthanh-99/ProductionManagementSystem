package com.tienthanh.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ImportMaterial;
import com.tienthanh.domain.ImportMaterialBill;
import com.tienthanh.domain.Material;
import com.tienthanh.domain.Supplier;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.ImportBillService;
import com.tienthanh.service.MaterialService;
import com.tienthanh.service.SupplierService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class ImportMaterialController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SupplierService supplierServie;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private FormatDateImpl formatDate;

	@Autowired
	private ImportBillService importBillService;

	private List<ImportMaterial> importMaterialList = new ArrayList<ImportMaterial>();

	private double total = 0;

	@RequestMapping("/importMaterial")
	public String importMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<Supplier> supplierList = supplierServie.findAll();
		model.addAttribute("supplierList", supplierList);

		List<Material> materialList = materialService.findAll();
		model.addAttribute("materialList", materialList);

		return "importMaterial";
	}

	@RequestMapping("/addImportMaterial")
	public String addImportMaterial(Model model, Principal principal, @RequestParam("supplierId") Long supplierId,
			@RequestParam("materialId") Long materialId, @RequestParam("quanlity") int quanlity) {
		Material material = materialService.findById(materialId);
		Supplier supplier = supplierServie.findById(supplierId);
		
		ImportMaterial importMaterial = new ImportMaterial();
		importMaterial.setMaterial(material);
		importMaterial.setSupplier(supplier);
		importMaterial.setQuanlity(quanlity);
		
		importMaterialList.add(importMaterial);
		
		return "redirect:/importMaterial";
	}

	@RequestMapping("/createImportBill")
	public String createImportBill(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);


		for (ImportMaterial importMaterial : importMaterialList) {
			total += importMaterial.getQuanlity() * importMaterial.getMaterial().getPriceImport();
		}

		model.addAttribute("importMaterialList", importMaterialList);
		model.addAttribute("total", total);
		return "importBill";
	}

	@RequestMapping("/cancelImportBill")
	public String cancelImportBill(Model model, Principal principal) {
		importMaterialList.clear();
		total = 0;
		return "redirect:/importMaterial";
	}

	@RequestMapping("saveImportBill")
	public String saveImportBill(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		ImportMaterialBill importMaterialBill = new ImportMaterialBill();
		importMaterialBill.setTotal(total);
		importMaterialBill.setImportMaterialList(importMaterialList);
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		importMaterialBill.setCreateDate(createDate);

		importBillService.createBill(importMaterialBill, importMaterialList, employee);

		importMaterialList.clear();
		total = 0;

		String message = "Tạo hóa đơn thành công!";
		model.addAttribute("message", message);

		return "importBill";
	}

	@RequestMapping("/historyImportMaterial")
	public String historyImportMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		Date localDate = formatDate.convertLocalDateTimeToDate(LocalDateTime.now());
		List<ImportMaterialBill> importlBillList = importBillService.findTenImportBill(localDate);
		model.addAttribute("importlBillList", importlBillList);

		return "historyImportMaterial";
	}

	@RequestMapping("/viewImportBill")
	public String viewImportBill(Model model, Principal principal, @ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		
		ImportMaterialBill importMaterialBill = importBillService.findById(id);
		model.addAttribute("importMaterialList", importMaterialBill.getImportMaterialList());
		model.addAttribute("total", importMaterialBill.getTotal());
		return "viewImportBill";
	}
}
