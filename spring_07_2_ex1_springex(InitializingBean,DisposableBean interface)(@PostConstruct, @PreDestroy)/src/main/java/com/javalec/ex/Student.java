package com.javalec.ex;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class Student implements InitializingBean, DisposableBean {

	private String name;
	private int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	//인터페이스 이용방법
	//InitializingBean를 implement한다면 반드시 afterPropertiesSet()을 오버라이드 해야한다
	//빈을 초기화 하는 과정 해서 생성된다.
	//MainClass에서 ctx.refresh(); 가 작동될떼 실행된다.
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
	}
	
	//인터페이스 이용방법
	//DisposableBean를 implement한다면 반드시 destroy() 을 오버라이드 해야한다
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()");
	}

}
