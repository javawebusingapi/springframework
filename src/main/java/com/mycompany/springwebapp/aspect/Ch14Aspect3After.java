package com.mycompany.springwebapp.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect3After {
																//*는 메소드(모든 메소드), (..)는 안에 뭐가 들어가던 상관 없음을 의미
	@After("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.after*(..))")
	public void weavingMethod() {
		log.info("after1 실행");
	}
}
