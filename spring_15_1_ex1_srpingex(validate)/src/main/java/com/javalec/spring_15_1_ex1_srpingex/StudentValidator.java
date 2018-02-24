package com.javalec.spring_15_1_ex1_srpingex;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


//Validator를 구현하면 supports와 validate... 2개의 메소드를 기본적으로 override해줘야 한다
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		//클래스 타입의 Student객체를 유효성을 검사 
		return Student.class.isAssignableFrom(arg0);  // 검증할 객체의 클래스 타입 정보
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("validate()");
		//obj에 어떤 객체가 들어올지는 모르지만 Student타입으로 캐스팅
		Student student = (Student)obj;
		
		String studentName = student.getName();
		if(studentName == null || studentName.trim().isEmpty()) {
			System.out.println("studentName is null or empty");
			errors.rejectValue("name", "trouble");
		}
		
		int studentId = student.getId();
		//id의 초기화를 안해주었기에 초기값은 0이다 
		if(studentId == 0) {
			System.out.println("studentId is 0");
			errors.rejectValue("id", "trouble");
		}
	}
	
}
