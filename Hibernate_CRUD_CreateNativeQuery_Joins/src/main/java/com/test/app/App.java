package com.test.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.test.Utility.HibernateUtility;
import com.test.entity.Address;
import com.test.entity.Country;
import com.test.entity.Employee;

/**
 * Hello world!
 *
 */
public class App extends Thread {

	public static void main(String[] args) {

//		Join_NativeQuery();
		// Native_Insert_Query();
		Join_NativeQuery22();
	}

	private static void Join_NativeQuery22() {
		System.out.println("select run...\n");
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
//			String SQL = "SELECT * from Employee empParent " + "LEFT JOIN Address adrChild "
//					+ "ON empParent.id = adrChild.employee_id " + "LEFT JOIN Country couChild "
//					+ "ON adrChild.aid = couChild.address_id"; // employee_id in address as Foreign key
//	==========> CORRECT Query <======================
//			select
//	        author0_.id as id1_0_0_,
//	        book2_.id as id1_1_1_,
//	        author0_.firstName as firstNam2_0_0_,
//	        author0_.lastName as lastName3_0_0_,
//	        author0_.version as version4_0_0_,
//	        book2_.publisherid as publishe5_1_1_,
//	        book2_.publishingDate as publishi2_1_1_,
//	        book2_.title as title3_1_1_,
//	        book2_.version as version4_1_1_,
//	        books1_.authorId as authorId2_2_0__,
//	        books1_.bookId as bookId1_2_0__ 
//	    from
//	        Author author0_ 
//	    inner join
//	        BookAuthor books1_ 
//	            on author0_.id=books1_.authorId 
//	    inner join
//	        Book book2_ 
//	            on books1_.bookId=book2_.id 
//	    where
//	        book2_.title like '%Hibernate%'
//  =========================================================================================

			String SQL1 = "SELECT empParent.id,empParent.fname,adrChild.aid,couChild.cid"
					+ " from Employee empParent "
					+ "LEFT JOIN Address adrChild ON empParent.id = adrChild.employee_id "
					+ "LEFT JOIN Country couChild ON adrChild.aid=couChild.address_id";

			List<Employee> employees = session.createNativeQuery(SQL1).addEntity(Address.class)
					.addEntity(Country.class)
					.addJoin("empParent", "empParent.adrChild")
					.addJoin("adrChild", "adrChild.couChild")
					.setResultTransformer(Criteria.ROOT_ENTITY)
					.list();
			int i = 0;
			for (Employee e : employees) {
				System.out.println("Employee" + " : " + i + " : " + e);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Join_NativeQuery() {
		System.out.println("select run...\n");
		// Parent dila ki child tyacha to manage karato using cascading.Alll,merge yane
		// a.employee_id check in class as Table Employee class/Entity.
//		String SQL = "SELECT * from Employee emp LEFT JOIN Address adr" + " ON emp.id = adr.employee_id"
//				+ " LEFT JOIN Country cou" + " ON adr.aid = cou.address_id";

		// parent fetch kara child pan fetch hoil eagar loading for employee tar address
		// ani country automatically
		// yetil.

		try (Session session = HibernateUtility.getSessionFactory().openSession()) {

			// Parent Employee Child Table Address Subchild Table Country
			// first join madhe child adr -> Address
			// second join madhe child cou -> Country

			@SuppressWarnings("unchecked")
			List<Employee> employees = session.createNativeQuery("SELECT id,fname,lname from Employee")
					.addEntity(Employee.class).list();
			int i = 0;
			for (Employee e : employees) {
				System.out.println("Employee" + " : " + i + " : " + e);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Native_Insert_Query() {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			if (session.isOpen()) {
				System.out.println("working...");
				session.beginTransaction();
				Employee employee = new Employee();
				employee.setFname("Mahesh");
				employee.setLname("Potdar");

				Address address1 = new Address();
				address1.setAname("Pune");

				Address address2 = new Address();
				address2.setAname("Mumbai");

				Address address3 = new Address();
				address3.setAname("New York");

				Address address4 = new Address();
				address4.setAname("White Houte D.C.");

				Address address5 = new Address();
				address5.setAname("Solhapur");

				address1.setEmployee(employee);
				address2.setEmployee(employee);
				address3.setEmployee(employee);
				address4.setEmployee(employee);
				address5.setEmployee(employee);

				Country country1 = new Country();
				Country country2 = new Country();
				Set<Address> addresses = new HashSet<Address>();
				addresses.add(address1);
				addresses.add(address2);
				addresses.add(address3);
				addresses.add(address4);
				addresses.add(address5);

				List<Address> addresses2 = addresses.stream().collect(Collectors.toList());
				employee.setAddress(addresses2);

				country1.setCname("INDIA");
				country2.setCname("United States of America");

				address1.setCountry(country1);
				address2.setCountry(country1);
				address5.setCountry(country1);
				address3.setCountry(country2);
				address4.setCountry(country2);

				List<Address> addressesINDIA = new ArrayList<Address>();
				addressesINDIA.add(address1);
				addressesINDIA.add(address2);
				addressesINDIA.add(address5);

				List<Address> addressesAmerica = new ArrayList<Address>();
				addressesAmerica.add(address3);
				addressesAmerica.add(address4);

				country1.setAddresses(addressesINDIA);
				country2.setAddresses(addressesAmerica);

				int idx = (Integer) session.save(employee);

				session.getTransaction().commit();
				if (idx > 0)
					System.out.println("Insert oprtaion Done!!!");
				else
					System.out.println("Noooooooooooooooooooooooooo!!!");
			} else {
				System.out.println("session not working !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
