package com.spring.factory2;

public class PrintableFactory {
	public static Printable getPrintable() {
		return new Apple();
		// return new Ball();
	}
}
