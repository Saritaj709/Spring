package com.bridgelabz.collectionnew;

import java.util.Iterator;
import java.util.List;

public class Questions {
	private int id;
	private String name;
	private List<Answer> answers;
	
	public Questions() {
	}

	public Questions(int id, String name, List<Answer> answers) {
		super();
		this.id = id;
		this.name = name;
		this.answers = answers;
	}

	public void show() {
		System.out.println(id + " " + name);
		System.out.println("Answers are ");
		Iterator<Answer> iteratr = answers.iterator();
		while (iteratr.hasNext()) {
			System.out.println(iteratr.next());
		}
	}
}
