package com.hib.test;

import org.hibernate.Session;

import com.hib.model.Employee;
import com.hib.model.Employee1;
import com.hib.util.HibernateUtil;


public class HibernateTest {
	/**
	 * @param arg
	 */
	public static void main(String arg[]){
		Employee employee = new Employee();		
		employee.setName("Mayur");
		employee.setRole("Sen");
		
		Session session = HibernateUtil.getSessinFactory().getCurrentSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		HibernateUtil.getSessinFactory().close();
	}
}
