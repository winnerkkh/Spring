package com.javalec.spring_15_1_ex1_srpingex;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

	@RequestMapping("/studentForm")
	public String studentForm() {
		return "createPage";
	}
	
	@RequestMapping("/student/create")
	//BindingResult 타입의 객체는 유효성 검증후 결과를 담아주는 객체
	public String studentCreate(@ModelAttribute("student") Student student, BindingResult result) {
		
		String page = "createDonePage";
		
		StudentValidator validator = new StudentValidator();
		validator.validate(student, result); //validator객체안으로 내가 받은 student의 커맨드 객체와 바인딩 객체를 매개변수로 전달
											 //valiidate가 유효성 검사를 해 결과를 있는지 없는지의 결과를 result객체에 저장
		if(result.hasErrors()) {
			page = "createPage";
		}
		
		return page;
	}
}
