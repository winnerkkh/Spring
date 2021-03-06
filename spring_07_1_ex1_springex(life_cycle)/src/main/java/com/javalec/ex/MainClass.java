package com.javalec.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(); // 컨테이너 생성

		ctx.load("classpath:applicationCTX.xml"); // 컨테이너 설정

		ctx.refresh();

		Student student = ctx.getBean("student", Student.class); // 컨테이너 사용
		System.out.println("이름 : " + student.getName());
		System.out.println("나이 : " + student.getAge());

		ctx.close(); // 컨테이너 자원 종료

	}

}
