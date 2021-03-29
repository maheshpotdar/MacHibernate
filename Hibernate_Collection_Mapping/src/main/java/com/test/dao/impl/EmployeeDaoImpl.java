package com.test.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.test.Utility.HibernateUtility;
import com.test.dao.EmployeeDao;
import com.test.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	public void UpdateEmployee(Employee employee) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();

			// Update Employee
			session.update(employee);
			Employee empFromDB = session.get(Employee.class, employee.getId());
			System.out.println("Employee Updated!!!");

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteEmployee(int id) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(session.get(Employee.class, id));
			try {
				Employee employee2 = session.get(Employee.class, id);
				if (employee2 != null)
					System.out.println("Failed to Delete");
				else
					System.out.println("Deletion Successfull.");
			} catch (Exception e) {
				System.out.println("Object not present!!!" + e.getMessage());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectAllEmployee() {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();

			List<Employee> employee2 = session.createQuery("FROM Employee").getResultList();
//			employee2.parallelStream().forEach(System.out::println);

			for (Employee e : employee2) {
				System.out.println(e);
			}
			session.getTransaction().commit();// commit means Permenent Changes.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void fetchSingleEmployee(Employee employee) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();

			Employee employee2 = session.get(Employee.class, employee.getId());
			if (employee != null)
				System.out.println("Employee from DB= " + employee2);
			else
				System.out.println(new Employee());
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void CreateEmployee(Employee employee) {
		try {
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();

			session.persist(employee);
//			if (pk > 0)
//				System.out.println("Employee Inserted Successfully.");
//			else
//				System.out.println("Failed to insert New Employee. ");
			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
