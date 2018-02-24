package com.javalec.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.ex.member.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("board/confirmId")
	//httpServletRequest로 데이타 객체가 들어오며(클라이언트에서 데이타를 받을때)
	//사용자가 요청한한 데이타를 받는 객체(model객체)는 데이타를 담아서 view쪽으로 데이타를 전송 사용
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id"); //해당 데이타의 이름을 적으면... String으로 반환값이 나온다
		String pw = httpServletRequest.getParameter("pw");
		model.addAttribute("id", id); //리퀘스트로 받은 데이타를 모델객체에 저장(새로운 변수 선언을 통한.) 
		model.addAttribute("pw", pw);
		return "board/confirmId";
	}
	
	@RequestMapping("board/checkId")
	//Annotation을 이용하여 파라미터에서 값을 바로 받는 경우	@RequestParam을 이용
	//만약 id와 pw가 값이 없이 전송된다면 ...400 error가 뜬다.
	//그렇기때문에 반드시 데이터가 필요함
	//@RequestParam("id") String id =>id의 어트리뷰트를 통하여 값을 받은후 String타입에 id에 그 값을 저장하겠다.
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		model.addAttribute("identify", id);
		model.addAttribute("password", pw);
		return "board/checkId";
	}
	
	
	//만약 데이타의 양이 많아 질경우 코드의 양이ㅏ 길어진다.
//	@RequestMapping("/member/join")
//	public String joinData(@RequestParam("name") String name, @RequestParam("id") String id, 
//			@RequestParam("pw") String pw, @RequestParam("email") String email, Model model) {
//		
//		Member member = new Member();
//		member.setName(name);
//		member.setId(id);
//		member.setPw(pw);
//		member.setEmail(email);
//		
//		model.addAttribute("memberInfo", member);
//		
//		return "member/join";
//	}
	
	
	@RequestMapping("/member/join")
	//http://localhost:8288/ex/member/join?name=%ED%99%8D%EA%B8%B8%EC%88%9C&id=abvasd&pw=1dfj4&email=ww.naver.com
	//커맨드 객체를 바로 파라미터에 넣어줘서 jsp에 넘기면 자동으로 연산해준다
	public String joinData(Member member) {
		
		return "member/join";
	}
	
}
