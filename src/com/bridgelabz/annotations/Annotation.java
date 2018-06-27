package com.bridgelabz.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})//for all level
//@Target(ElementType.METHOD) for method level
@interface Student {
	int id() default 10;

	String name() default "tom";
}

class Annotation {
	Annotation(int id, String name) {
		System.out.println(id);
		System.out.println(name);
	}

	@Student(id = 22, name = "tom")
	public void studentDetails() {
		System.out.println("id and name are : ");
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Annotation annotation = new Annotation(20, "mumbai");
		//Class method=annotation.method.getClass(Annotation.class);
		Method method = annotation.getClass().getMethod("studentDetails");
		Student student = method.getAnnotation(Student.class);
		System.out.println("name is " + student.name());
		System.out.println("id is " + student.id());
	}
}
