package com.mycompany.springwebapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect4AfterReturning {
																//*는 메소드(모든 메소드), (..)는 안에 뭐가 들어가던 상관 없음을 의미
	@AfterReturning(
			pointcut="execution(public * com.mycompany.springwebapp.controller.Ch14Controller.afterReturning*(..))",
			returning="returnValue")
	public void weavingMethod(String returnValue) {
		log.info("afterReturning 실행");
		log.info("리턴값 : " + returnValue);
	}
}
