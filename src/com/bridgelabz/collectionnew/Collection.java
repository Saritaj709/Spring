package com.bridgelabz.collectionnew;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Collection {
	public static void main(String[] args) {
		Resource resource=new ClassPathResource("Spring.xml");
		BeanFactory factory=new XmlBeanFactory(resource);
	//ApplicationContext factory = new ClassPathXmlApplicationContext("Employee.xml");
	    Answer answer=(Answer)factory.getBean("answer1");
	    answer.toString();
	     answer=(Answer)factory.getBean("answer2");
	    answer.toString();
		Questions question = (Questions)factory.getBean("question1");
		question.show();
	}
}
