package com.tienthanh.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.tienthanh.domain.DayPlan;

public interface DayPlanRepository extends CrudRepository<DayPlan, Long> {
	DayPlan findByDate(Date date);
}
