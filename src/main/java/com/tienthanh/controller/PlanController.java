package com.tienthanh.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienthanh.domain.LineWeekPlan;
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

	private LocalDateTime localDateTime = LocalDateTime.now();

	@RequestMapping("/plan")
	public String plan(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		localDateTime = LocalDateTime.now();

		List<WeekPlan> weekPlanList = planService
					.findByDate(formatDate.convertLocalDateTimeToDate(localDateTime));
		if (!weekPlanList.isEmpty()) {
			WeekPlan weekPlan = weekPlanList.get(0);
			model.addAttribute("weekPlan", weekPlan.getWeekPlan());
			model.addAttribute("id", weekPlan.getId());
			model.addAttribute("status", true);
		} else {
			String message = "Plan not Found!";
			model.addAttribute("message", message);
		}
		return "plan";
	}

	@RequestMapping("/nextPlan")
	public String nextPlan(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		localDateTime = localDateTime.plusDays(7);
		List<WeekPlan> weekPlanList = planService.findByDate(formatDate.convertLocalDateTimeToDate(localDateTime));
		if (!weekPlanList.isEmpty()) {
			WeekPlan weekPlan = weekPlanList.get(0);
			model.addAttribute("weekPlan", weekPlan.getWeekPlan());
			model.addAttribute("id", weekPlan.getId());
			model.addAttribute("status", true);
		} else {
			String message = "Plan not Found!";
			model.addAttribute("message", message);
		}
		return "plan";
	}

	@RequestMapping("/previousPlan")
	public String previoysPlan(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);
		localDateTime = localDateTime.minusDays(7);
		List<WeekPlan> weekPlanList = planService.findByDate(formatDate.convertLocalDateTimeToDate(localDateTime));
		if (!weekPlanList.isEmpty()) {
			WeekPlan weekPlan = weekPlanList.get(0);
			model.addAttribute("weekPlan", weekPlan.getWeekPlan());
			model.addAttribute("id", weekPlan.getId());
			model.addAttribute("status", true);
		} else {
			String message = "Plan not Found!";
			model.addAttribute("message", message);
		}
		return "plan";
	}

	@RequestMapping("/newPlan")
	public String newPlan(Model model, Principal principal) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<ProductionLine> productionLineList = productionLineService.findAll();

		List<LineWeekPlan> LinePlanList = new ArrayList<LineWeekPlan>();

		WeekPlan weekPlan = new WeekPlan();

		for (int i = 0; i < productionLineList.size(); i++) {
			LineWeekPlan lineWeekPlan = new LineWeekPlan();
			lineWeekPlan.setProductionLine(productionLineList.get(i));
			LinePlanList.add(lineWeekPlan);
		}

		weekPlan.setWeekPlan(LinePlanList);
		model.addAttribute("weekPlan", weekPlan);
		return "newPlan";
	}

	@PostMapping("/addNewPlan")
	public String addNewPlan(Model model, Principal principal, @ModelAttribute("weekPlan") WeekPlan weekPlan) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<ProductionLine> productionLineList = productionLineService.findAll();

		LocalDateTime localDateTime = LocalDateTime.now();
		Date createDate = formatDate.convertLocalDateTimeToDate(localDateTime);
		weekPlan.setCreateDate(createDate);

		planService.createWeekPlan(weekPlan, productionLineList, employee);
		model.addAttribute("weekPlan", weekPlan);

		String message = "Sucessfull! Add New Plan Complelte!";
		model.addAttribute("message", message);
		return "newPlan";
	}

	@RequestMapping("/updatePlan")
	public String updatePlan(Model model, Principal principal, @ModelAttribute("id") Long id) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		WeekPlan weekPlan = planService.findById(id);
		model.addAttribute("weekPlan", weekPlan);

		return "updatePlan";
	}

	@PostMapping("/updatePlan")
	public String updatePlanPost(Model model, Principal principal, @ModelAttribute("weekPlan") WeekPlan weekPlan) {
		Employee employee = employeeService.findByUsername(principal.getName());
		model.addAttribute("employee", employee);

		List<ProductionLine> productionLineList = productionLineService.findAll();

		WeekPlan updateWeekPlan = planService.findById(weekPlan.getId());
		LocalDateTime localDateTime = LocalDateTime.now();
		Date modifyDate = formatDate.convertLocalDateTimeToDate(localDateTime);
		updateWeekPlan.setModifyDate(modifyDate);

		for (int i = 0; i < weekPlan.getWeekPlan().size(); i++) {
			weekPlan.getWeekPlan().get(i).setId(updateWeekPlan.getWeekPlan().get(i).getId());
		}

		updateWeekPlan.setWeekPlan(weekPlan.getWeekPlan());
		planService.updateWeekPlan(updateWeekPlan, productionLineList, employee);
		model.addAttribute("weekPlan", updateWeekPlan);

		String message = "Sucessfull! Update Plan Complete!";
		model.addAttribute("message", message);
		return "updatePlan";
	}

}
