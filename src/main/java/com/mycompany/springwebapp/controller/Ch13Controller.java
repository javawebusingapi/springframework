package com.mycompany.springwebapp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dao.Ch13BoardDaoOld;
import com.mycompany.springwebapp.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13") //이 컨트롤러는 ch13이 포함이된다면 선택이 된다. 뒤의 메소드는 ch13을 빼버려도 된다.
public class Ch13Controller {
	
	@Resource
	private Ch13BoardDaoOld boardDaoOld;
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch13 content 실행");
		return "ch13/content";
	}
	
	@GetMapping("/insertboard")
	public String insertboard (){
		log.info("insertboard 실행");
		
		Ch13Board board = new Ch13Board();
		board.setBtitle("졸리다");
		board.setBcontent("그냥 졸리다");
		board.setMid("user");
		
		boardDaoOld.insert(board);
		
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/getboardList")
	public String getboardList (){
		log.info("getboardList 실행");
		boardDaoOld.selectAll();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/updateboard")
	public String updateboard() {
		log.info("updateboard 실행");
		boardDaoOld.updateByBno();
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/deleteboard")
	public String deleteboard() {
		log.info("deleteboard 실행");
		boardDaoOld.deleteByBno();
		return "redirect:/ch13/content";
	}
}