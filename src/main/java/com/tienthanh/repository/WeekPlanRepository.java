package com.tienthanh.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tienthanh.domain.WeekPlan;

public interface WeekPlanRepository extends CrudRepository<WeekPlan, Long> {
	@Query(value = "SELECT * FROM week_plan WHERE start_time <= :date AND end_time >= :date ;", nativeQuery = true)
	List<WeekPlan> findWeekByDate(@Param("date") Date date);
}
