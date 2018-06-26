package com.bridgelabz.spring;

public class Address { // Constructor injection with  Dependent object
	private String city;
	private String state;
	private String country;

	public Address(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String show() {
		return city + " " + state + " " + country;
	}
}
