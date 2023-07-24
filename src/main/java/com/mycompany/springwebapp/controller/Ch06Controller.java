package com.mycompany.springwebapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch06") //이 컨트롤러는 ch06이 포함이된다면 선택이 된다. 뒤의 메소드는 ch06을 빼버려도 된다.
public class Ch06Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch06 content 실행");
		return "ch06/content";
	}
	
	@GetMapping("/forward1")
	public String forward1(HttpServletRequest request) {
		request.setAttribute("loginStatus", false);
		request.setAttribute("userName", "홍길동");
		log.info("foward1 메소드 실행");
		return "ch06/forward1";
	}
	
	@GetMapping("/redirect")
	public String redirect(HttpServletRequest request, HttpSession session) throws Exception{
		// 방법 1)
		String userName = "홍길동";
		//request.setAttribute("userName", userName); (x) //request는 응답이 가고나면 삭제를 하는데 redirect도 응답이기 때문에 쓸 수 없다.
		log.info("redirect 메소드 실행");
		userName = URLEncoder.encode(userName, "UTF-8");
		
		// 방법 2)
		session.setAttribute("userId", "summer");
		
		
		return "redirect:/ch06/getValue?userName="+userName;
	}
	
	@GetMapping("/getValue")
	public String getValue(String userName, HttpServletRequest request, HttpSession session) {
		log.info("getValue 메소드 실행");
		//응답이 다시 돌아오면서 redirect 메소드에서 쓴 userName을 받을 수 있다.
		//방법 1-1)
		log.info("userName : " + userName);
		//방법 1-2)
		log.info("userName : " + request.getParameter("userName"));
		//방법 2)
		log.info("userId : " + session.getAttribute("userId"));
		return "redirect:/ch06/forward1";
	}
}