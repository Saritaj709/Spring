package com.spring.autowiring;

public class Apple {
	Ball ball;

	Apple() {
		System.out.println("Apple is created");
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	void print() {
		System.out.println("Hello apple");
	}

	void display() {
		print();
		ball.print();
	}
}
