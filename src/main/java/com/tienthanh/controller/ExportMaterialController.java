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
import com.tienthanh.domain.ExportMaterialBill;
import com.tienthanh.domain.ImportMaterial;
import com.tienthanh.domain.ImportMaterialBill;
import com.tienthanh.domain.Material;
import com.tienthanh.domain.ProductionLine;
import com.tienthanh.domain.Supplier;
import com.tienthanh.domain.UseMaterial;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.ExportBillService;
import com.tienthanh.service.ImportBillService;
import com.tienthanh.service.MaterialService;
import com.tienthanh.service.ProductionLineService;
import com.tienthanh.service.SupplierService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class ExportMaterialController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProductionLineService productionLineService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private FormatDateImpl formatDate;

	@Autowired
	private ExportBillService exportBillService;

	private List<UseMaterial> exportMaterialList = new ArrayList<UseMaterial>();

	@RequestMapping("/exportMaterial")
	public String exportMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<ProductionLine> productionLineList = productionLineService.findAll();
		model.addAttribute("productionLineList", productionLineList);

		List<Material> materialList = materialService.findAll();
		model.addAttribute("materialList", materialList);

		return "exportMaterial";
	}

	@RequestMapping("/addExportMaterial")
	public String addExportMaterial(Model model, Principal principal,
			@RequestParam("productionLineId") Long productionLineId,
			@RequestParam("materialId") Long materialId, @RequestParam("quanlity") int quanlity) {
		Material material = materialService.findById(materialId);
		ProductionLine productionLine = productionLineService.findById(productionLineId);
		
		UseMaterial exportMaterial = new UseMaterial();
		exportMaterial.setMaterial(material);
		exportMaterial.setProductionLine(productionLine);
		exportMaterial.setQuanlity(quanlity);
		
		exportMaterialList.add(exportMaterial);
		
		return "redirect:/exportMaterial";
	}

	@RequestMapping("/createExportBill")
	public String createExportBill(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		model.addAttribute("exportMaterialList", exportMaterialList);
		return "exportBill";
	}

	@RequestMapping("/cancelExportBill")
	public String cancelExportBill(Model model, Principal principal) {
		exportMaterialList.clear();
		return "redirect:/importMaterial";
	}

	@RequestMapping("saveExportBill")
	public String saveExportBill(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		ExportMaterialBill exportMaterialBill = new ExportMaterialBill();
		exportMaterialBill.setUseMaterialList(exportMaterialList);
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		exportMaterialBill.setCreateDate(createDate);

		exportBillService.createBill(exportMaterialBill, exportMaterialList, employee);

		exportMaterialList.clear();

		String message = "Tạo hóa đơn thành công!";
		model.addAttribute("message", message);

		return "exportBill";
	}

	@RequestMapping("/historyExportMaterial")
	public String historyExportMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		Date localDate = formatDate.convertLocalDateTimeToDate(LocalDateTime.now());
		List<ExportMaterialBill> exportlBillList = exportBillService.findTenExportBill(localDate);
		model.addAttribute("exportlBillList", exportlBillList);

		return "historyExportMaterial";
	}

	@RequestMapping("/viewExportBill")
	public String viewExportBill(Model model, Principal principal, @ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		
		ExportMaterialBill exportMaterialBill = exportBillService.findById(id);
		model.addAttribute("exportMaterialList", exportMaterialBill.getUseMaterialList());

		return "viewExportBill";
	}
}
