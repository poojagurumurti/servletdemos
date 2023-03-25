package com.hibernatehelloworld;

import org.hibernate.Session;

import com.hibernatehelloworld.domain.Message;
import com.hibernatehelloworld.utils.HibernateUtil;

public class HibernateDemo {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		Message message = new Message("HelloWorld from Hibernate using annotations");
		
		session.save(message);
		session.getTransaction().commit();
		session.close();
	}
}
