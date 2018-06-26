package com.bridgelabz.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Employee1Test {
	public static void main(String[] args) {
		Resource resource=new ClassPathResource("Spring.xml");
		BeanFactory factory=new XmlBeanFactory(resource);
	//	ApplicationContext factory=new ClassPathXmlApplicationContext("Spring.xml");
		Employee1 employee=(Employee1)factory.getBean("employee1");
		employee.show();
		System.out.println(employee.hashCode());
		Employee1 employee1=(Employee1)factory.getBean("employee1");
		employee1.show();
		System.out.println(employee1.hashCode());
	}
}
