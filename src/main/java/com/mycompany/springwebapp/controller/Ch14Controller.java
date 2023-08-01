package com.mycompany.springwebapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.aspect.Login;
import com.mycompany.springwebapp.aspect.RuntimeCheck;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch14") //이 컨트롤러는 ch14이 포함이된다면 선택이 된다. 뒤의 메소드는 ch14을 빼버려도 된다.
public class Ch14Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch14 content 실행");
		return "ch14/content";
	}
	
	@RequestMapping("/before")
	public String before() {
		log.info("before 실행");
		return "redirect:/ch14/content";
	}
	
	@RequestMapping("/after")
	public String after() {
		log.info("after 실행");
		return "redirect:/ch14/content";
	}
	
	@RequestMapping("/afterReturning")
	public String afterReturning() {
		log.info("afterReturning 실행");
		return "redirect:/ch14/content";
	}
	
	@RequestMapping("/afterThrowing")
	public String afterThrowing() {
		log.info("afterThrowing 실행");
		boolean result = true;
		if(result) {
			throw new RuntimeException("테스트예외 발생");
		}
		return "redirect:/ch14/content";
	}
	
	@RequestMapping("/around")
	public String around() {
		log.info("around 실행");
		return "redirect:/ch14/content";
	}
	
	@Resource
	private Ch13BoardService boardService;
	
	@RequestMapping("/runtimeCheck")
	@RuntimeCheck
	public String getBoardList() {
		int totalBoardNum = boardService.getTotalBoardNum();
		Ch13Pager pager = new Ch13Pager(5, 5, totalBoardNum, 1);
		
		List<Ch13Board> list = boardService.getList(pager);
		return "redirect:/ch14/content";
	}
	
	@RequestMapping("/loginCheck")
	@Login
	public String loginCheck() {
		log.info("로그인 실행");
		
		return "redirect:/ch14/content";
	}
	
}