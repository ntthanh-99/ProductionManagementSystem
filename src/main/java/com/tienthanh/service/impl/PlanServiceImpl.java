package com.tienthanh.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.DayPlan;
import com.tienthanh.domain.WeekPlan;
import com.tienthanh.repository.DayPlanRepository;
import com.tienthanh.repository.WeekPlanRepository;
import com.tienthanh.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private DayPlanRepository dayPlanRepository;

	@Autowired
	private WeekPlanRepository weekPlanRepository;

	@Autowired
	private FormatDateImpl formatDate;

	@Override
	public List<DayPlan> findByDate(LocalDateTime localDateTime) {
		// TODO Auto-generated method stub
		List<DayPlan> dayPlanList = new ArrayList<DayPlan>();

		List<Date> dateList = formatDate.findDaysOfWeek(localDateTime);

		for (Date date : dateList) {
			dayPlanList.add(dayPlanRepository.findByDate(date));
		}

		return dayPlanList;
	}

	@Override
	public WeekPlan findByDate(Date date) {
		// TODO Auto-generated method stub
		return weekPlanRepository.findWeekByDate(date).get(0);
	}

}
