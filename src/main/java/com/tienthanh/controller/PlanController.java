package com.tienthanh.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienthanh.domain.DayPlan;
import com.tienthanh.domain.Employee;
import com.tienthanh.domain.ProductionLine;
import com.tienthanh.domain.WeekPlan;
import com.tienthanh.service.EmployeeService;
import com.tienthanh.service.PlanService;
import com.tienthanh.service.ProductionLineService;
import com.tienthanh.service.impl.FormatDateImpl;

@Controller
public class PlanController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FormatDateImpl formatDate;

	@Autowired
	private ProductionLineService productionLineService;

	@Autowired
	private PlanService planService;

	@RequestMapping("/plan")
	public String plan(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		WeekPlan weekPlan = planService.findByDate(formatDate.convertLocalDateTimeToDate(LocalDateTime.now()));
		model.addAttribute("weekPlan", weekPlan.getWeekPlan());
		return "plan";
	}

	@RequestMapping("/newPlan")
	public String newPlan(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<ProductionLine> productionLineList = productionLineService.findAll();
		model.addAttribute("productionLineList", productionLineList);
		
		List<String> statusList = new ArrayList<String>();
		for(int i=1;i<=7*productionLineList.size();i++) {
			statusList.add(new String());
		}
		model.addAttribute("statusList", statusList);
		return "newPlan";
	}

}
