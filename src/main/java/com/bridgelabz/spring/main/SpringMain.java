package com.bridgelabz.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgelabz.spring.service.EmployeeService;

public class SpringMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		EmployeeService employeeService = context.getBean("employeeService", EmployeeService.class);
		System.out.println(employeeService.getEmployee().getName());
		employeeService.getEmployee().setName("Tom");
		employeeService.getEmployee().setId(1309);
		employeeService.getEmployee().throwException();
		context.close();
	}
}
