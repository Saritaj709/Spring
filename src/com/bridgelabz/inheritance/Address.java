package com.bridgelabz.inheritance;

public class Address {
	private String addressLine;
	private String city;
	private String state;
	private String country;

	public Address(String addressLine, String city, String state, String country) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String toString() {
		return addressLine + " " + city + " " + state + " " + country;
	}
}
