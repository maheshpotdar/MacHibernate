package com.test.service.impl;

import com.test.dao.EmployeeDao;
import com.test.dao.impl.EmployeeDaoImpl;
import com.test.entity.Employee;
import com.test.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	public void UpdateEmployee(Employee employee) {
		new EmployeeDaoImpl().UpdateEmployee(employee);
	}

	public void deleteEmployee(int id) {
		new EmployeeDaoImpl().deleteEmployee(id);
	}

	public void selectAllEmployee() {
		new EmployeeDaoImpl().selectAllEmployee();
	}

	public void fetchSingleEmployee(Employee employee) {
		new EmployeeDaoImpl().fetchSingleEmployee(employee);
	}

	public void CreateEmployee(Employee employee) {
		new EmployeeDaoImpl().CreateEmployee(employee);
	}

}
