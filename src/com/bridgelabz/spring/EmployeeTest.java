package com.bridgelabz.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class EmployeeTest {
public static void main(String[] args) {
	Resource resource=new ClassPathResource("Spring.xml");
	BeanFactory factory=new XmlBeanFactory(resource);
	Employee employee=(Employee)factory.getBean("employee");
	employee.show();
}
}
