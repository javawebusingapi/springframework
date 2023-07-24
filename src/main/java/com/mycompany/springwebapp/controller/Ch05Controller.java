package com.mycompany.springwebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch05") //이 컨트롤러는 ch05이 포함이된다면 선택이 된다. 뒤의 메소드는 ch05을 빼버려도 된다.
public class Ch05Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch05 content 실행");
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(@RequestHeader("User-Agent") String userAgent, HttpServletRequest request) {
		log.info("User-Agent : " + userAgent);
		log.info("Client IP : " + request.getRemoteAddr());
		return "redirect:/ch05/content";
	}
	
	@RequestMapping(value="/createCookie", method=RequestMethod.GET)
	public String createCookie(HttpServletResponse response) {
		log.info("쿠키생성 메소드 실행");
		Cookie cookie = new Cookie("useremail", "summer@mycompany.com");
		cookie.setDomain("localhost"); 			//쿠키를 재전송 서버를 지정 (localhost일때 본인 ip인 192.168.0.191로 전송 할 경우 전송이 되지 않는다.)
		//cookie.setDomain("com.mycompany"); 	//회사에서 쓰는 서버 전부 쿠키를 생성하고 싶을 때 Domain 앞이 같으면 뒤에 뭐가와도 생성이 가능
		cookie.setPath("/"); 					// Domain뒤에 붙는 것 Path안에 붙은 주소일때만 요청 (같은 서버 내에서 경로 지정)
		cookie.setMaxAge(1800);					//쿠키의 저장 기간 (단위 : 초), 시간을 주지 않으면 브라우저 메모리에서만 쿠키를 저장하기 떄문에 브라우저 종료시 사라짐. (원래는 파일에 저장)
		cookie.setHttpOnly(true);				// true : 서버만 이용, false : JavaScript에서 접근 허용
		cookie.setSecure(false);				// false : http, https 모두 쿠키를 재전송 // true : https만 쿠키를 재전송
		//정보가 헤드에 실려서 넘어간다.
		response.addCookie(cookie);
		return "redirect:/ch05/content";
	}
	
	@RequestMapping(value="/getCookie", method=RequestMethod.GET)
	public String getCookie(@CookieValue("useremail") String userEmail) {
		log.info("쿠키가져오기 메소드 실행");
		log.info("useremail: " + userEmail);
		return "redirect:/ch05/content";
	}
}