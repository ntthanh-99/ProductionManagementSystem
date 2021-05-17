package com.tienthanh.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DayPlan extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dayPlan")
	private List<LinePlan> dayPlan;
	
	@ManyToOne
	@JoinColumn(name = "week_plan_id")
	private WeekPlan weekPlan;
	
	public DayPlan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WeekPlan getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(WeekPlan weekPlan) {
		this.weekPlan = weekPlan;
	}

	public List<LinePlan> getDayPlan() {
		return dayPlan;
	}

	public void setDayPlan(List<LinePlan> dayPlan) {
		this.dayPlan = dayPlan;
	}
	
}
