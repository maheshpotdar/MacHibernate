package com.test.service;

import com.test.entity.Employee;

public interface EmployeeService {

	public abstract void UpdateEmployee(Employee employee);
	public abstract void deleteEmployee(int id);
	public abstract void selectAllEmployee();
	public abstract void fetchSingleEmployee(Employee employee);
	public abstract void CreateEmployee(Employee employee);
	
}
