package com.mycompany.springwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.service.Ch12ServiceDiByAnnotation;
import com.mycompany.springwebapp.service.Ch12ServiceDiByXml;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //@로 객체를 쓰려면 Ch12Controller또한 관리객체가 되어야하는데 이를 @Controller가 해준다.
@RequestMapping("/ch12") //이 컨트롤러는 ch12이 포함이된다면 선택이 된다. 뒤의 메소드는 ch12을 빼버려도 된다.
public class Ch12Controller {
	/*@Resource(name="name1")
	private Ch12ServiceCreateByXml service1;*/
	
	@Autowired
	//주입되는 객체는 xml에서 bean으로 주입한것이 자동으로 주입된다.
	private Ch12ServiceDiByXml serviceDiByXml;
	
	@Autowired
	private Ch12ServiceDiByAnnotation serviceDiByAnnotation;
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch12 content 실행");
		serviceDiByXml.method();
		serviceDiByAnnotation.method();
		return "ch12/content";
	}
	
	
}