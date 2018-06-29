package com.bridgelabz.spring.model;

import java.io.Serializable;

import com.bridgelabz.spring.aspect.Loggable;

public class Employee implements Serializable {
	private String name;
    private int id;
	public int getId() {
		return id;
	}
   
	@Loggable
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Loggable
	public void setName(String name1) {
		this.name = name1;
	}

	public void throwException() {
		throw new RuntimeException("Dummy Exception");
	}

}
