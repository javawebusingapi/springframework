package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch15") //이 컨트롤러는 ch15이 포함이된다면 선택이 된다. 뒤의 메소드는 ch13을 빼버려도 된다.
public class Ch15Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch15 content 실행");
		return "ch15/content";
	}
	
	
}