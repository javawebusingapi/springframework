package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
}