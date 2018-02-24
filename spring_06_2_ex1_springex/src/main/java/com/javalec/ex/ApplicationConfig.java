package com.javalec.ex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //"이 클래스는 스프링 설정에 사용되는 클래스입니다" 라고 명시해주는 어노테이션
public class ApplicationConfig { 

	@Bean //객체 설정(=> Student 타입의  student1을 만든다)
	public Student student1(){
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("수영");
		hobbys.add("요리");
		
		Student student = new Student("홍길동", 20, hobbys); //생성자의 파라미터가 3개이기에 3개의 값을 넣어줌
		
		//나머지들은 setter를 이용해 초기화를 해줌
		student.setHeight(180);
		student.setWeight(80);
		
		return student;
	}
	
	@Bean
	public Student student2(){
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("독서");
		hobbys.add("음악감상");
		
		Student student = new Student("홍길순", 18, hobbys);
		student.setHeight(170);
		student.setWeight(55);
		
		return student;
	}
	
}
