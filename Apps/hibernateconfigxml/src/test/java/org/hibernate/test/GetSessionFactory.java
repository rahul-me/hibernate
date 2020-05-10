package org.hibernate.test;

import org.hib.cfg.HibernateUtil;
import org.hibernate.SessionFactory;

public class GetSessionFactory {
	public static void main(String arg[]){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.close();
		
	}
}
