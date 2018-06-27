package com.spring.autowiring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("autowire.xml");
		Apple apple = context.getBean("apple", Apple.class);
		Ball ball= context.getBean("ball", Ball.class);
		/*
		 * Resource resource=new ClassPathResource("Spring.xml"); BeanFactory
		 * factory=new XmlBeanFactory(resource); Apple
		 * apple=(Apple)factory.getBean("apple");
		 */
		ball.print();
		apple.display();
	}
}
