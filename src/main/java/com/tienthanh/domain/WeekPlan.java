package com.tienthanh.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WeekPlan extends AbstractClass{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int month;
	private String time;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "weekPlan")
	private List<DayPlan> weekPlan;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public WeekPlan() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<DayPlan> getWeekPlan() {
		return weekPlan;
	}

	public void setWeekPlan(List<DayPlan> weekPlan) {
		this.weekPlan = weekPlan;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
