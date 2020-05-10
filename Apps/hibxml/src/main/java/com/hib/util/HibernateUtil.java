package com.hib.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hib.model.Employee;
import com.hib.model.Employee1;

public class HibernateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	// xml based
	private static SessionFactory sessionFactory;
	
	//annotation based configuration
	private static SessionFactory sessionAnnotationFactory;
	
	//property based configuration
	private static SessionFactory sessionJavaConfigFactory;
	
	private static SessionFactory buildSessionFactory(){
		try {
			//create the session factory from hibernate.cfg.xml
			
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.addResource("Employee.hbm.xml");
			logger.info("hibernate configuration has been loaded");
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			logger.info("hibernate service registery created");
			
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable ex){
			logger.info(ex.toString());
			throw new ExceptionInInitializerError();
		}
	}
	
	private static SessionFactory buildSessionAnnotationFactory(){
		try {
			//create the session factory from hibernate-annotation.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate-annotation.cfg.xml");
			configuration.addAnnotatedClass(Employee1.class);
			logger.info("hibernate configuration has been loaded");
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			logger.info("hibernate service registery created");
			
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
			
		} catch(Throwable ex){
			logger.error(ex.toString());
			throw new ExceptionInInitializerError();
		}
	}
	
	private static SessionFactory buildSessionFactoryJavaConfig(){
		try {
			Configuration configuration = new Configuration();
			
			Properties properties =  new Properties();
			properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/expdata");
			properties.put("hibernate.connection.username", "root");
			properties.put("hibernate.connection.password", "ric#101991");
			properties.put("hibernate.current_session_context_class", "thread");
			
			configuration.setProperties(properties);
			
			configuration.addAnnotatedClass(Employee1.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			
			return configuration.buildSessionFactory(serviceRegistry);
			
			
		} catch(Throwable ex){
			logger.info(ex.toString());
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SessionFactory getSessinFactory(){
		if(sessionFactory == null){
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;		
	}
	
	public static SessionFactory getSesssionAnnotationFactory(){
		if(sessionFactory == null){
			sessionFactory = buildSessionAnnotationFactory();
		}
		return sessionFactory;
	}
	
	public static SessionFactory getSessionJavaConfigFactory(){
		if(sessionFactory == null){
			sessionFactory = buildSessionFactoryJavaConfig();
		}
		return sessionFactory;
	}
}
