package com.mycompany.springwebapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch15Account;
import com.mycompany.springwebapp.service.Ch15AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch15") //이 컨트롤러는 ch15이 포함이된다면 선택이 된다. 뒤의 메소드는 ch13을 빼버려도 된다.
public class Ch15Controller {
	
	@Resource
	private Ch15AccountService accountservice;
	
	@RequestMapping("/content")
	public String content(Model model) {
		log.info("ch15 content 실행");
		
		List<Ch15Account> list = accountservice.getAccounts();
		model.addAttribute("list",list);
		return "ch15/content";
	}
	
	
	@RequestMapping("/transfer")
	public String transfer(int fromAno, int toAno, int amount, HttpSession session) {
		accountservice.transfer(fromAno, toAno, amount);
		session.removeAttribute("transferError");
		return "redirect:/ch15/content";
	}
}