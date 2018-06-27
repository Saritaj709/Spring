package com.bridgelabz.maps;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Map;

public class Question {
	private int id;
	private String name;
	private Map<String, String> answer;

	public Question() {
	}

	public Question(int id, String name, Map<String, String> answer) {
		super();
		this.id = id;
		this.name = name;
		this.answer = answer;
	}

	public void display() {
		System.out.println("question id : " + id);
		System.out.println("question name : " + name);
		System.out.println("Answers....");
		Set<Entry<String, String>> set = answer.entrySet();
		Iterator<Entry<String, String>> iteratr = set.iterator();
		while (iteratr.hasNext()) {
			Entry<String, String> entry = iteratr.next();
			System.out.println("Answer : " + entry.getKey() + " posted by : " + entry.getValue());
		}
	}
}
