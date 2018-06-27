package com.spring.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldTest {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Hello.class);
		HelloWorld helloWorld = context.getBean(HelloWorld.class);
		helloWorld.setMessage("Hi Hi Hi :) ");
		helloWorld.getMessage();
	}
}
