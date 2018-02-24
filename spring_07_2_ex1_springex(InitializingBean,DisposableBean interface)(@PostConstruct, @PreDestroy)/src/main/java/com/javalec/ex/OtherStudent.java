package com.javalec.ex;

import javax.annotation.*;

public class OtherStudent  {

	private String name;
	private int age;
	
	public OtherStudent(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	//Annotation이용방법(개발자가 메소드를 만든후 어노테이션만 붙인다.)
	@PostConstruct
	public void initMethod() {
		System.out.println("initMethod()");
	}
	
	
	//Annotation이용방법(개발자가 메소드를 만든후 어노테이션만 붙인다.)
	@PreDestroy
	public void destroyMethod() {
		System.out.println("destroyMethod()");
	}

}
