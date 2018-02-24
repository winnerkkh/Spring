package com.javalec.spring_15_3_ex1_srpingex;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

	/*
	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
		
		String page = "createDonePage";
		
		StudentValidator validator = new StudentValidator();
		validator.validate(student, result);
		if(result.hasErrors()) {
			page = "createPage";
		}
		
		return page;
	}
	*/
/*	
 	pom에 추가해줘야한다
 	
	<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-validator</artifactId>
	<version>4.2.0.Final</version>
	</dependency>
	
	*/
	
	@RequestMapping("/student/create")
	
	 /*유효성 검사를 할 객체에다가 @Valid 어노테이션을 붙쳐준다*/
	
	
	public String studentCreate(@ModelAttribute("student") @Valid Student student, BindingResult result) {
		
		String page = "createDonePage";
		
//		StudentValidator validator = new StudentValidator();
//		validator.validate(student, result);
		if(result.hasErrors()) {
			page = "createPage";
		}
		
		return page;
	}
	
	 /* @InitBinder 어노테이션을 붙쳐준다...유효성 검사 객체를 넣어준다
	       그리고 나서 그럼 따로 유효성 검사를 안해줘도 스프링에서 자체적으로 유효성 검사를 해줌*/
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new StudentValidator());
	}
	
}
