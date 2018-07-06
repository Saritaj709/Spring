package com.bridgelabz.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.bridgelabz.*"})
public class HelloWorld {
	public static void main(String[] args) {
		SpringApplication.run(HelloWorld.class,args);	}
}
