package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch11Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch11") //이 컨트롤러는 ch11이 포함이된다면 선택이 된다. 뒤의 메소드는 ch11을 빼버려도 된다.
public class Ch11Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch11 content 실행");
		
		return "ch11/content";
	}	
	
	@GetMapping("/form1")
	public String form1(@ModelAttribute("member") Ch11Member member) {
		member.setMid("summer");
		member.setMname("홍길동");
		member.setMpassword("12345");
		member.setMnation("한국");
		return "ch11/form1";
	}
	
	@PostMapping("/form1")
	public String handleform1(@ModelAttribute("member") Ch11Member member) {
		log.info(member.toString());
		return "redirect:/ch11/content";
	}
}
