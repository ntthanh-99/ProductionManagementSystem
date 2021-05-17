package com.tienthanh.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.LineWeekPlan;
import com.tienthanh.domain.ProductionLine;
import com.tienthanh.domain.WeekPlan;
import com.tienthanh.repository.LineWeekkPlanRepository;
import com.tienthanh.repository.WeekPlanRepository;
import com.tienthanh.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private LineWeekkPlanRepository lineWeekPlanRepository;

	@Autowired
	private WeekPlanRepository weekPlanRepository;

	@Autowired
	private FormatDateImpl formatDate;

	@Override
	public List<WeekPlan> findByDate(Date date) {
		// TODO Auto-generated method stub
		return weekPlanRepository.findWeekByDate(date);
	}

	@Override
	public WeekPlan createWeekPlan(WeekPlan weekPlan, List<ProductionLine> productionLineList, Employee employee) {
		// TODO Auto-generated method stub
		weekPlan.setEmployee(employee);
		LocalDateTime localDateTime = LocalDateTime.now();
		List<Date> dates = formatDate.findDaysOfWeek(localDateTime.plusDays(7));
		weekPlan.setStartTime(dates.get(0));
		weekPlan.setEndTime(dates.get(6));

		for (int i = 0; i < weekPlan.getWeekPlan().size(); i++) {
			weekPlan.getWeekPlan().get(i).setProductionLine(productionLineList.get(i));
			weekPlan.getWeekPlan().get(i).setWeekPlan(weekPlan);
		}
		weekPlanRepository.save(weekPlan);
		return weekPlan;
	}

	@Override
	public WeekPlan updateWeekPlan(WeekPlan weekPlan, List<ProductionLine> productionLineList, Employee employee) {
		// TODO Auto-generated method stub
		for (int i = 0; i < weekPlan.getWeekPlan().size(); i++) {
			weekPlan.getWeekPlan().get(i).setProductionLine(productionLineList.get(i));
			weekPlan.getWeekPlan().get(i).setWeekPlan(weekPlan);
		}
		weekPlan.setEmployee(employee);
		return weekPlanRepository.save(weekPlan);
	}

	@Override
	public WeekPlan findById(Long id) {
		// TODO Auto-generated method stub
		return weekPlanRepository.findById(id).get();
	}

}
