package com.tienthanh.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.tienthanh.domain.DayPlan;
import com.tienthanh.domain.WeekPlan;

public interface PlanService {
	List<DayPlan> findByDate(LocalDateTime localDateTime); // nhap vao ngay -> tra ve lich ca tuan

	WeekPlan findByDate(Date date);
}
