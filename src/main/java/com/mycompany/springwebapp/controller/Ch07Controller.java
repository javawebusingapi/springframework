package com.mycompany.springwebapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springwebapp.dto.Ch07Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch07") //이 컨트롤러는 ch07이 포함이된다면 선택이 된다. 뒤의 메소드는 ch07을 빼버려도 된다.
public class Ch07Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch07 content 실행");
		return "ch07/content";
	}
	
	@GetMapping("/useRequest1")
	public String useRequest1(HttpServletRequest request) {
		List<Ch07Board> boards = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Ch07Board b = new Ch07Board();
			b.setBno(i);
			b.setBtitle("제목"+i);
			b.setBcontent("내용"+i);
			b.setBwriter("글쓴이"+i);
			b.setBdate(new Date());
			boards.add(b);
		}
		request.setAttribute("boardList", boards);
		
		return "ch07/request";
	}
	
	@GetMapping("/useRequest2")
	public ModelAndView useRequest2() {
		List<Ch07Board> boards = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Ch07Board b = new Ch07Board();
			b.setBno(i);
			b.setBtitle("제목"+i);
			b.setBcontent("내용"+i);
			b.setBwriter("글쓴이"+i);
			b.setBdate(new Date());
			boards.add(b);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boards); //request에 저장하는 것과 동일
		mav.setViewName("ch07/request");
		return mav;
	}
	
	@GetMapping("/useRequest3")
	public String useRequest3(Model model) {
		List<Ch07Board> boards = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Ch07Board b = new Ch07Board();
			b.setBno(i);
			b.setBtitle("제목"+i);
			b.setBcontent("내용"+i);
			b.setBwriter("글쓴이"+i);
			b.setBdate(new Date());
			boards.add(b);
		}
		
		model.addAttribute("boardList", boards);
		
		return "ch07/request";
	}
	
	
	@GetMapping("/useRequest4")
	public String useRequest4 (Ch07Board board) {
		//request.setAttribute("ch07Board", board); //이와 같은 형태로 첫 자가 소문자로 된 클래스 이름으로 자동 저장된다. 
		log.info(board.toString());
		return "ch07/request";
	}
	
	@GetMapping("/useRequest5")
	public String useRequest5 (@ModelAttribute("newBoard") Ch07Board board) {
		//request.setAttribute("newBoard", board); //이와 같은 형태로 첫 자가 소문자로 된 클래스 이름으로 자동 저장된다. 
		return "ch07/request";
	}
}