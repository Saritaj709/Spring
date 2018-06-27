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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	void show() {
		System.out.println(id + " " + name);
		System.out.println(address.show());
	}
}
