package com.test.dao;

import org.hibernate.Session;

import com.test.entity.Employee;

public interface EmployeeDao {

	public abstract void UpdateEmployee(Employee employee);
	public abstract void deleteEmployee(int id);
	public abstract void selectAllEmployee();
	public abstract void fetchSingleEmployee(Employee employee);
	public abstract void CreateEmployee(Employee employee);
}
