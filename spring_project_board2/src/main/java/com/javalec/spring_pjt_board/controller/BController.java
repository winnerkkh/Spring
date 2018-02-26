package com.javalec.spring_pjt_board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.command.BContentCommand;
import com.javalec.spring_pjt_board.command.BDeleteCommand;
import com.javalec.spring_pjt_board.command.BListCommand;
import com.javalec.spring_pjt_board.command.BModifyCommand;
import com.javalec.spring_pjt_board.command.BReplyCommand;
import com.javalec.spring_pjt_board.command.BReplyViewCommand;
import com.javalec.spring_pjt_board.command.BWriteCommand;
import com.javalec.sptring_pjt_board.util.Constant;



//컨트롤러 역할을 하기 위해선 오토스캔에 걸려야하기 때문에
//@Controller Annotation을 붙여준다
@Controller
public class BController {
	
	//인터페이스인 BCommand타입의 command변수 선언
	BCommand command;
	
	//JDBC 템플렛 필드 선언
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
		System.out.println("list() method has started");
		//인터페이스 타입의 command변수에 implemented된 BListCommand객체 대입(같은 타입임)
		command = new BListCommand();
		command.execute(model);
		return "list";
	}
	

	//동작이 아니라 작성하는 화면이기에 form으로만 넘기면됨
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	//HttpServletRequest request를 파라미터로 사용하는 이유는
	// write_view에 form에서 작성된 데이터를 받기위하여 사용됨 
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		System.out.println("write() method has started");
		model.addAttribute("request", request); //reuqet속성을 정해서 통째로 대입(컨트롤로에서 작업을 안하고 서비스에서 작업을 하기 때문)
		command = new BWriteCommand();
		command.execute(model);
		
		//작성을 다한후 list.jsp로 이동해서 전체글을 확인시켜주는 로직
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	//list페이지에서 bId값을 가져와서  HttpServletRequest request를 파라미터에 기입한거임
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	//콘텐츠 안에 내용을 변경하로 /modify로 넘어오는데, post방식으로 넘어옴
	@RequestMapping(method= RequestMethod.POST, value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		//request 객체 안에는 content_view에서 수정된 모든 내용들이 저장됨
		model.addAttribute("request", request);; //받은 데이타를 통쨰로 request 속을을 정해서 통째로 대입
		command = new BModifyCommand();
		command.execute(model);
	 
		//작성을 다한후 list.jsp로 이동해서 전체글을 확인시켜주는 로직
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
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
