package com.test.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.test.Utility.HibernateUtility;
import com.test.entity.Address;
import com.test.entity.Employee;
import com.test.service.EmployeeService;
import com.test.service.impl.EmployeeServiceImpl;

/**
 * Hello world!
 *
 */
public class App extends Thread {

	private static EmployeeService employeeService = null;

	public App() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		super.run();
	}

	static {
		employeeService = new EmployeeServiceImpl();
	}

	public static void main(String[] args) {
		App a = new App();

		createEmployeeStarter();
		// selectAllEmployee();
	}

	private static void createEmployeeStarter() 
	{
		//HQL main table main insert nahi kar sakate lekin 1 table ka data dusare table insert kar sakate hai.
		
//		Employee[] employees = CreateEmployee();
//		for (Employee e : employees) {
//			employeeService.CreateEmployee(e);
//		}
		
		String HQL="INSERT INTO employeedummy(id,fname,lname) SELECT id,fname,lname from Employee";
		try
		{
			Session session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
				
				javax.persistence.Query query= session.createQuery(HQL);
				int idx= query.executeUpdate();	
				if(idx>0)
					System.out.println("INSERTION Successfully!!!");
				
				session.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Employee[] CreateEmployee() {

		Employee employee1 = new Employee();
		employee1.setFname("Mahesh");
		employee1.setLname("Potdar");

		Employee employee2 = new Employee();
		employee2.setFname("Shawn");
		employee2.setLname("Paul");

		Employee employee3 = new Employee();
		employee3.setFname("Mac");
		employee3.setLname("destine");

		Employee employee4 = new Employee();
		employee4.setFname("Ross");
		employee4.setLname("champiy");

		Employee employee5 = new Employee();
		employee5.setFname("jenis");
		employee5.setLname("ganta");

		// ======== Address ==========

		Address address1 = new Address();
		address1.setCountryName("Kolhapur");

		Address address2 = new Address();
		address2.setCountryName("Pune");

		List<Address> lstOfAddress = new ArrayList<Address>();
		lstOfAddress.add(address1);
		lstOfAddress.add(address2);

		// ==============================

		employee1.setAddress(lstOfAddress);
		address1.setEmployee(employee1);
		address2.setEmployee(employee1);

		Employee[] emp = new Employee[] { employee1, employee2, employee3, employee4, employee5 };

		return emp;
	}

	private static void selectAllEmployee() {
		employeeService.selectAllEmployee();
	}

}
