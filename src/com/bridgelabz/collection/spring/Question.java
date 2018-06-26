package com.bridgelabz.collection.spring;

import java.util.Iterator;
import java.util.List;

public class Question {
	private int id;
	private String name;
	private List<String> answers;

	public Question() {
	}

	public Question(int id, String name, List<String> answers) {
		super();
		this.id = id;
		this.name = name;
		this.answers = answers;
	}

	public void show() {
		System.out.println(id + " " + name);
		System.out.println("Answers are ");
		Iterator<String> iteratr = answers.iterator();
		while (iteratr.hasNext()) {
			System.out.println(iteratr.next());
		}
	}
}
