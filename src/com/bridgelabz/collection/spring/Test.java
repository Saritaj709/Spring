package com.bridgelabz.collection.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {
	public static void main(String[] args) // Constructor injection with collection example
	{
		Resource resource=new ClassPathResource("Spring.xml");
		BeanFactory factory=new XmlBeanFactory(resource);
		//ApplicationContext factory = new ClassPathXmlApplicationContext("Spring.xml");
		Question question = (Question)factory.getBean("question");
		question.show();
		System.out.println(question.hashCode());
	}
}
