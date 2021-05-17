package com.tienthanh.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.tienthanh.domain.Employee;
import com.tienthanh.domain.LineWeekPlan;
import com.tienthanh.domain.ProductionLine;
import com.tienthanh.domain.WeekPlan;

public interface PlanService {
	List<WeekPlan> findByDate(Date date);

	WeekPlan createWeekPlan(WeekPlan weekPlan, List<ProductionLine> productionLineList, Employee employee);

	WeekPlan updateWeekPlan(WeekPlan weekPlan, List<ProductionLine> productionLineList, Employee employee);

	WeekPlan findById(Long id);
}
