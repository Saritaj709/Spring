package com.bridgelabz.maps;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
public class Map {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("Employee.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		Question question = (Question)factory.getBean("q");
		question.display();
	}
}
