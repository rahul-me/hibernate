package org.hib.cfg;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	public static SessionFactory getSessionFactory(){
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
		Properties properties = configuration.getProperties();
		ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.applySettings(properties).build();
		
		return configuration.buildSessionFactory(serviceRegistry);		
	}
}
