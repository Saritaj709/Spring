package com.spring.factory2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("Spring.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		//ApplicationContext context = new
		// ClassPathXmlApplicationContext("Spring.xml");

		// Printable p = (Printable) context.getBean("p");
		Printable p1 = (Printable) factory.getBean("p");
		//p.print();
		p1.print();
	}
}
