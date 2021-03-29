package com.test.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.test.Utility.HibernateUtility;
import com.test.entity.Employee;

/**
 * Hello world!
 *
 */
public class App extends Thread {

	public static void main(String[] args) {

		//String SQL = "select * from Employee_Table";
		String SQL = "select * from Employee_Table";
		//NativeQueriesDemoAllColoumns(SQL);
		//insertEmployee();

		//NativeQueriesDemoSignleColoumn(SQL);
		
		//NativeQueriesDemoByEntities(SQL);
		NativeQueriesDemoByEntities2(SQL);
		
	}
	
	private static void NativeQueriesDemoByEntities(String SQL) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			//native query ork with SQL Table not class 
			List<Employee> lstOfEmployee = session.createNativeQuery(SQL).addEntity(Employee.class).list();
			 
			for(Employee o: lstOfEmployee)
			{ 
				System.out.println(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void NativeQueriesDemoByEntities2(String SQL) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			//native query ork with SQL Table not class 
			List<Employee> lstOfEmployee = session.createNativeQuery(SQL,Employee.class).list();
			 
			for(Employee o: lstOfEmployee)
			{ 
				System.out.println(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void NativeQueriesDemoSignleColoumn(String SQL) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			//native query ork with SQL Table not class 
			List<Object[]> lstOfEmployee = session.createNativeQuery(SQL).addScalar("id", IntegerType.INSTANCE).addScalar("lname", StringType.INSTANCE).list();
			 
			for(Object[] o: lstOfEmployee)
			{
				Object id = o[0];
				Object firstName = o[1];
				System.out.println(id+":"+firstName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void NativeQueriesDemoAllColoumns(String SQL) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			//native query ork with SQL Table not class 
			List<Object[]> lstOfEmployee = session.createNativeQuery(SQL).list();
			for (Object[] e : lstOfEmployee) {
				int id = (int) e[0];
				String fname = (String) e[1];
				String lname = (String) e[2];
				System.out.println(id+" : "+fname+" : "+lname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertEmployee() {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			session.beginTransaction();
			int idX = 0;
			for (Employee e : getListEmployee()) {
				idX = (int) session.save(e);
			}
			if (idX>0) {
				System.out.println("Insertion completed.");
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Employee> getListEmployee() {
		List<Employee> lst = new ArrayList<Employee>();
		lst.add(new Employee(10, "Mahesh", "Patel"));
		lst.add(new Employee(20, "Ganesh", "Patil"));
		lst.add(new Employee(30, "Jignesh", "More"));
		lst.add(new Employee(40, "Raj", "Koshti"));
		lst.add(new Employee(50, "Chandu", "Singh"));
		lst.add(new Employee(60, "Pandurangu", "Saluka"));
		lst.add(new Employee(70, "Shiraj", "Mujawar"));
		lst.add(new Employee(80, "Viraj", "Dobriyal"));
		lst.add(new Employee(90, "Kamlesh", "Pataleshwari"));
		lst.add(new Employee(100, "Chitra", "Dev"));
		return lst;
	}
}
