package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		
		//객체에서 HttpServletRequest 내용을 뽑아낸다.
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		//Content_view에 있는 내용들을 변수를 선언에서 저장
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		//싱글톤 메소드
		BDao dao = BDao.getInstance();
		dao.modify(bId, bName, bTitle, bContent);
		
	}

}
