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

import com.tienthanh.domain.Distribution;
import com.tienthanh.domain.DistributionBill;
import com.tienthanh.domain.Distributor;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ExportMaterialBill;
import com.tienthanh.domain.ImportMaterial;
import com.tienthanh.domain.ImportMaterialBill;
import com.tienthanh.domain.Material;
import com.tienthanh.domain.Product;
import com.tienthanh.domain.ProductionLine;
import com.tienthanh.domain.Supplier;
import com.tienthanh.domain.UseMaterial;
import com.tienthanh.service.DistributionBillService;
import com.tienthanh.service.DistributorService;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.ExportBillService;
import com.tienthanh.service.ImportBillService;
import com.tienthanh.service.MaterialService;
import com.tienthanh.service.ProductService;
import com.tienthanh.service.ProductionLineService;
import com.tienthanh.service.SupplierService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class DistributionController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProductService productService;

	@Autowired
	private DistributorService distributorService;

	@Autowired
	private FormatDateImpl formatDate;

	@Autowired
	private DistributionBillService distributionBillService;

	private List<Distribution> distributionList = new ArrayList<Distribution>();

	private double total = 0;

	@RequestMapping("/distribution")
	public String distribution(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<Distributor> distributorList = distributorService.findAll();
		model.addAttribute("distributorList", distributorList);

		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);

		return "distribution";
	}

	@RequestMapping("/addDistribution")
	public String addDistribution(Model model, Principal principal, @RequestParam("distributorId") Long distributorId,
			@RequestParam("productId") Long productId, @RequestParam("quanlity") int quanlity) {
		Distributor distributor = distributorService.findById(distributorId);
		Product product = productService.findById(productId);
		
		Distribution distribution = new Distribution();
		distribution.setProduct(product);
		distribution.setDistributor(distributor);
		distribution.setQuanlity(quanlity);
		
		distributionList.add(distribution);
		
		return "redirect:/distribution";
	}

	@RequestMapping("/createDistributionBill")
	public String createDistributionBill(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		for (Distribution distribution : distributionList) {
			total += distribution.getQuanlity() * distribution.getProduct().getPrice();
		}

		model.addAttribute("distributionList", distributionList);
		model.addAttribute("total", total);

		return "distributionBill";
	}

	@RequestMapping("/cancelDistributionBill")
	public String cancelDistributionBill(Model model, Principal principal) {
		distributionList.clear();
		total = 0;
		return "redirect:/importMaterial";
	}

	@RequestMapping("saveDistributionBill")
	public String saveExportBill(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		DistributionBill distributionBill = new DistributionBill();
		distributionBill.setDistributionList(distributionList);
		LocalDateTime createDateNow = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(createDateNow);
		distributionBill.setCreateDate(createDate);

		distributionBillService.createBill(distributionBill, distributionList, employee);

		distributionList.clear();
		total = 0;
		String message = "Create Bill Complete!";
		model.addAttribute("message", message);

		return "exportBill";
	}

	@RequestMapping("/historyDistribution")
	public String historyExportMaterial(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		Date localDate = formatDate.convertLocalDateTimeToDate(LocalDateTime.now());
		List<DistributionBill> distributionBillList = distributionBillService.findAll(localDate);
		model.addAttribute("distributionList", distributionBillList);

		return "historyDistribution";
	}

	@RequestMapping("/viewDistributionBill")
	public String viewExportBill(Model model, Principal principal, @ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		
		DistributionBill distributionBill = distributionBillService.findById(id);
		model.addAttribute("distributionList", distributionBill.getDistributionList());

		return "viewDistributionBill";
	}
}
