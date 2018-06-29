package com.bridgelabz.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeAnnotationAspect {

	@Before("@annotation(com.bridgelabz.spring.aspect.Loggable)")
	public void myAdvice() {
		System.out.println("executing MyAdvice!!");
	}
}
