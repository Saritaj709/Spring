package com.spring.factory2;

public class Printablefactory1 {
//non static method
	public Printable getPrintable() {
		return new Apple();
	}
}
