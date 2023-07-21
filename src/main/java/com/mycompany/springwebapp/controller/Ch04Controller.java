package com.mycompany.springwebapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch04Form1;
import com.mycompany.springwebapp.dto.Ch04Form2;
import com.mycompany.springwebapp.dto.Ch04Join;
import com.mycompany.springwebapp.dto.Ch04Login;
import com.mycompany.springwebapp.validator.Ch04Form1Validator;
import com.mycompany.springwebapp.validator.Ch04Form2Validator;
import com.mycompany.springwebapp.validator.Ch04JoinValidator;
import com.mycompany.springwebapp.validator.Ch04LoginValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch04") //이 컨트롤러는 ch03이 포함이된다면 선택이 된다. 뒤의 메소드는 ch03을 빼버려도 된다.
public class Ch04Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch04 content 실행");
		return "ch04/content";
	}
	
	//클래스명의 첫번째글자를 소문자로 바꾼것을 key값을 가짐
	@InitBinder("ch04Form1")
	public void Ch04Form1Validator(WebDataBinder binder) {
		binder.setValidator(new Ch04Form1Validator());	
	}
	@InitBinder("ch04Form2")
	public void Ch04Form2Validator(WebDataBinder binder) {
		binder.setValidator(new Ch04Form2Validator());	
	}
	@InitBinder("ch04Join")
	public void Ch04JoinValidator(WebDataBinder binder) {
		binder.setValidator(new Ch04JoinValidator());	
	}
	@InitBinder("ch04Login")
	public void Ch04LoginValidator(WebDataBinder binder) {
		binder.setValidator(new Ch04LoginValidator());	
	}
	@PostMapping("/method1")
	//pom.xml validation api 라이브러리 의존설정 필요. 
	//(Request에 저장 ) request.setAttribute("ch04Form1',form1);로 자동 저장
	public String method1(@Valid Ch04Form1 form1, Errors errors) {
		//errors.rejectValue(...)가 한번이라도 호출되었다면 hasErrors()는 true를 리턴 한다.
		if(errors.hasErrors()) {
			return "ch04/content";
		}
		
		//요청 처리 코드
		log.info("param1: " + form1.getParam1());
		log.info("param2: " + form1.getParam2());
		log.info("param3: " + form1.getParam3());
		log.info("param4: " + form1.isParam4());
		log.info("param5: " + form1.getParam5());
		
		return "redirect:/";
	}
	
	@PostMapping("/method2")
	//pom.xml validation api 라이브러리 의존설정 필요. 
	//(Request에 저장 ) request.setAttribute("ch04Form2',form2);로 자동 저장
	public String method2(@Valid Ch04Form2 form2, Errors errors) {
		//errors.rejectValue(...)가 한번이라도 호출되었다면 hasErrors()는 true를 리턴 한다.
		if(errors.hasErrors()) {
			log.info(errors.toString());
			return "ch04/content";
		}
		
		//요청 처리 코드
		log.info("param1: " + form2.getParam21());
		log.info("param2: " + form2.getParam22());
		log.info("param3: " + form2.getParam23());
		log.info("param4: " + form2.isParam24());
		log.info("param5: " + form2.getParam25());
		
		return "redirect:/";
	}
	
	@PostMapping("/join")
	//pom.xml validation api 라이브러리 의존설정 필요. 
	//(Request에 저장 ) request.setAttribute("ch04Join',join);로 자동 저장
	public String join(@Valid Ch04Join join, Errors errors) {
		//errors.rejectValue(...)가 한번이라도 호출되었다면 hasErrors()는 true를 리턴 한다.
		if(errors.hasErrors()) {
			return "ch04/content";
		}
		
		//요청 처리 코드
		log.info("mid: " + join.getMid());
		log.info("mpassword: " + join.getMpassword());
		log.info("mgetmemail: " + join.getMemail());
		log.info("mtel: " + join.getMtel());
		
		return "redirect:/";
	}
	
	@PostMapping("/login")
	//pom.xml validation api 라이브러리 의존설정 필요. 
	//(Request에 저장 ) request.setAttribute("ch04Join',join);로 자동 저장
	public String login(@Valid Ch04Login login, Errors errors) {
		//errors.rejectValue(...)가 한번이라도 호출되었다면 hasErrors()는 true를 리턴 한다.
		if(errors.hasErrors()) {
			return "ch04/content";
		}
		
		//요청 처리 코드
		log.info("mid: " + login.getMid());
		log.info("mpassword: " + login.getMpassword());
		
		return "redirect:/";
	}
}