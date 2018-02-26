package com.javalec.springMVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.springMVCBoard.command.BCommand;
import com.javalec.springMVCBoard.command.BContentCommand;
import com.javalec.springMVCBoard.command.BDeleteCommand;
import com.javalec.springMVCBoard.command.BListCommand;
import com.javalec.springMVCBoard.command.BModifyCommand;
import com.javalec.springMVCBoard.command.BReplyCommand;
import com.javalec.springMVCBoard.command.BReplyViewCommand;
import com.javalec.springMVCBoard.command.BWriteCommand;
import com.javalec.springMVCBoard.util.Constant;

/**
 * Servlet implementation class BoardFrontController
 */

@Controller
public class BController {

	//인터페이스인 BCommand타입의 command변수 선언
	BCommand command;
	
	//JDBC 템플렛 필드(변수) 선언
	public JdbcTemplate template;
	
	//JDBC setter 선언
	//아래와같이 명시해주면 서블릿 xml에서 설정된
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		//콘트롤러에 template설정한 변수를 세터를 이용해서 xml에 설정된 name값인 template
		//컨트롤러 template에 할당
		this.template = template;
		
		//Constant는 static 변수로서 util패키지 안에 있다.
		//Controller안에 template 변수에 할당된값을 Constant안에 template변수에 할당
		//Constant의 template변수는 static이기에, 이렇게 할당해두면 언제든지 사용가능
		Constant.template = this.template;
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		System.out.println("list()");
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST )
	public String modify(HttpServletRequest request, Model model){
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model){
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);		
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
}
