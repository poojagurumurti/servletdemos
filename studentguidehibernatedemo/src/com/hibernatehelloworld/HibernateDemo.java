package com.hibernatehelloworld;

import org.hibernate.Session;

import com.hibernatehelloworld.domain.Address;
import com.hibernatehelloworld.domain.Person;
import com.hibernatehelloworld.utils.HibernateUtil;

public class HibernateDemo {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		Address address1 = new Address("1124 Home street", "Mumbai", "600123");
		Address address2 = new Address("2124 Home street", "Chennai", "700123");
		Person person = new Person("Max", address1,address2);
		session.save(person);
		session.getTransaction().commit();
		session.close();
	}
}
