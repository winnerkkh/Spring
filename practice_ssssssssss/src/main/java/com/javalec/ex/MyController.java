package com.javalec.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	
	@RequestMapping("/view")
	public String view() {
		
		return "view";
	}
	
	@RequestMapping("contents")
	public String contentview(Model model) {
		
		model.addAttribute("id", "abcd");
		
		return "contents";
	}
	
	@RequestMapping("model/modelex")
	public String modelEx(Model model) {
		
		model.addAttribute("data", "showmethemoney");
		
		return "model/modelex";
	}
	
	
	
	@RequestMapping("modelAndView/modelView")
	
	//파라미터가 필요 없다(modelAndView)
	//데이터와 view를 같이 가지고 있다.
	public ModelAndView modelAndView() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("name", "model이다");
		mv.setViewName("modelAndView/modelView");
		
		return mv;
	}
}
