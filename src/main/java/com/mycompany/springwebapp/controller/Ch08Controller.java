package com.mycompany.springwebapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.Interceptor.Login;
import com.mycompany.springwebapp.dto.Ch08Item;
import com.mycompany.springwebapp.dto.Ch08Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch08") //이 컨트롤러는 ch08이 포함이된다면 선택이 된다. 뒤의 메소드는 ch08을 빼버려도 된다.
public class Ch08Controller {
	
	@RequestMapping("/content")
	public String content(HttpSession session) {
		log.info("ch08 content 실행");
		return "ch08/content";
	}
	@PostMapping("/login")
	public String login(Ch08Member member, HttpSession session) {
		if(member.getMid().equals("photo1") && member.getMpassword().equals("12345")) {
			member.setMname("하여름");
			member.setMtel("010-1234-5678");
			member.setMaddress("서울시 종로구 혜화동");
			session.setAttribute("login",member);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	@Login
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/ch08/content";
	}
	
	@PostMapping("/addCart")									//@SessionAtrribute를 사용하여 만들기
	@Login
	public String addCart(Ch08Item item, HttpSession session/*, @SessionAttribute ("cart") List<Ch08Item> cart*/) {
		//세션에서 카트 가져오기 (직접 List를 만들기 둘중 하나만 가능)
		List<Ch08Item> cart = (ArrayList<Ch08Item>) session.getAttribute("cart");
		
		//세션에 카트가 없을 경우, 새로 저장해서 세션에 지정
		if(cart == null) {
			cart = new ArrayList<Ch08Item>();
			session.setAttribute("cart", cart);
		}
		
		//카트에 해당 아이템이 있는지 조사
		boolean exist = false;
		for(Ch08Item cartItem : cart) {
			if(cartItem.getName().equals(item.getName())) {
				//카트에 기존 아이템이 있을 경우, 수량 수정
				cartItem.setAmount(cartItem.getAmount() + item.getAmount());
				exist = true;
			}
		}
		
		//카트에 없는새로운 아이템이 있는 경우.
		if(exist == false) {
			cart.add(item);
		}
		
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/clearCart")
	@Login
	public String clearCart(Ch08Item item, HttpSession session) {
		//카트를 삭제
		
		//방법 1 : 세션에 저장된 객체를 삭제
		session.removeAttribute("cart");
		
		//방법 2 : 세션 자체를 무효화 (위험함)(로그아웃 등의 작업을 할 때 사용)
		//session.invalidate();
		
		return "redirect:/ch08/content";
	}
}