package com.mycompany.springwebapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch02") //이 컨트롤러는 ch01이 포함이된다면 선택이 된다. 뒤의 메소드는 ch01을 빼버려도 된다.
public class Ch02Controller {
	//@Slf4j를 쓰지 않는 경우
	//private static final Logger log = LoggerFactory.getILoggerFactory(Ch01Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch02 content 실행");
		return "ch02/content";
	}
	
	@GetMapping("/method") //주의점 : jsp에서 쓴 bkind와 bkind의 변수명이 같아야한다 (bno또한 동일)
	public String method1(String bkind, int bno) {
		log.info("bkind: " + bkind);
		log.info("bno : " + bno);
		return "ch02/content";
	}
	
	@PostMapping("/method") //주의점 : jsp에서 쓴 bkind와 bkind의 변수명이 같아야한다 (bno또한 동일)
	public String method2(String bkind, int bno) {
		log.info("bkind: " + bkind);
		log.info("bno : " + bno);
		return "ch02/content";
	}
}
