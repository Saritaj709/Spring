package com.bridgelabz.spring;

public class Employee1 {
	private int id;
	private String name;
	private Address address; // Aggression

	public Employee1() {
		System.out.println("Defining constructors : ");
	}

	public Employee1(int id, String name, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	void show() {
		System.out.println(id + " " + name);
		System.out.println(address.show());
	}
}
