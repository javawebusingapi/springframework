package com.mycompany.springwebapp.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12ServiceCreateByXml {
	//private를 주면 빈을 사용할 수가 없다. //이는 factory를 이용
	public Ch12ServiceCreateByXml() {
		log.info("Ch12ServiceCreateByXml 생성자 실행 (객체 생성 확인)");
	}
	
	public static Ch12ServiceCreateByXml getInstance() {
		log.info("getInstance 메소드 실행");
		return new Ch12ServiceCreateByXml();
	}
	
	public Ch12ServiceCreateByXml getSelfObject() {
		return new Ch12ServiceCreateByXml(); 
	}
	
	public void method1() {
		log.info("method1 실행");
	}
	
	public void method2() {
		log.info("method2 실행");
	}
}
