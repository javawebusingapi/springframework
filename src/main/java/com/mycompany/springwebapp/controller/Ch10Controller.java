package com.mycompany.springwebapp.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.exception.Ch10SoldOutException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch10") //이 컨트롤러는 ch10이 포함이된다면 선택이 된다. 뒤의 메소드는 ch10을 빼버려도 된다.
public class Ch10Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch10 content 실행");
		return "ch10/content";
	}
	
	@GetMapping("/handlingException1")
	public String handlingException1(String data) {
		try {
			if(data.equals("java")) {
				
			}
		} catch (Exception e) {
			return "ch10/500";
		}
		return "redirect:/ch10/content";
	}
	
	@GetMapping("/handlingException2")
	public String handlingException2(String data) {
		if(data.equals("java")) {
			//NullPointerException 활성
		}
		return "redirect:/ch10/content";
		
	}
	
	@GetMapping("/handlingException3")
	public String handlingException3() {
		Object data = "abc";
		Date date = (Date) data;
		//ClassCastException 발생
		return "redirect:/ch10/content";
		
	}
	
	@GetMapping("/handlingException4")
	public String handlingException4() {
		int stock = 0;
		if(stock == 0) {
			throw new Ch10SoldOutException("상품 잔고가 없어요");
		}
		//Ch10SoldOutException 발생
		return "redirect:/ch10/content";
		
	}
	
	@GetMapping("/handlingException5")
	public String handlingException5() {
		String data = "abc";
		int number = Integer.parseInt(data);
		//NumberFormatException 발생
		return "redirect:/ch10/content";
		
	}
}