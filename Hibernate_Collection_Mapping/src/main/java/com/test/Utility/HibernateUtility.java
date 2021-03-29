package com.test.Utility;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.test.entity.Employee;

public class HibernateUtility {

	private static SessionFactory sessionFactory = null;
	private static StandardServiceRegistry standardServiceRegistry = null;

	static {
		if (sessionFactory == null) {
			try {
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

			// Without hibernate.cfg.xml file using MAP.
			// Map<String, String> map = ConfigurationWithoutHibernate_cfg_xml_File();
			// standardServiceRegistryBuilder.applySettings(map);

			MetadataSources sources = new MetadataSources(standardServiceRegistry);
			Metadata metadata = sources.getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			}
			catch (Exception e) {
				e.printStackTrace();
				if(standardServiceRegistry == null)
					StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}

	private static Map<String, String> ConfigurationWithoutHibernate_cfg_xml_File() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		map.put(Environment.URL, "jdbc:mysql://localhost:3306/studentManagementHibernate2");
		map.put(Environment.USER, "root");
		map.put(Environment.PASS, "root");
		map.put(Environment.HBM2DDL_AUTO, "update");
		map.put(Environment.SHOW_SQL, "true");
		map.put(Environment.FORMAT_SQL, "true");
		map.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		return map;
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	public static void shutdownSessionFactory() {
		sessionFactory.close();
	}

	public HibernateUtility() {
		System.out.println("Constructor Called...");
	}

}
