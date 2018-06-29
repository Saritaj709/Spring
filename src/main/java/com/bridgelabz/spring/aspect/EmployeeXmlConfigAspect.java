package com.bridgelabz.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class EmployeeXmlConfigAspect {

	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("EmployeeXmlConfigAspect::before invoking getName() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("EmployeeXmlConfigAspect::after invoking getName() method, Return value=" + value);
		return value;
	}
}
