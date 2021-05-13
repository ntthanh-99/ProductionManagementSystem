package com.tienthanh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tienthanh.domain.Employee;
import com.tienthanh.repository.EmployeeRepository;

@Service
public class UserSecurityService implements UserDetailsService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findByUsername(username);
		if(employee==null) {
			throw new UsernameNotFoundException("Employee not Found!");
		}
		return employee;
	}
	
}
