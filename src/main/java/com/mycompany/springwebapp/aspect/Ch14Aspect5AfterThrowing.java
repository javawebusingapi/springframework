package com.mycompany.springwebapp.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect5AfterThrowing {
																//*는 메소드(모든 메소드), (..)는 안에 뭐가 들어가던 상관 없음을 의미
	@AfterThrowing(
			pointcut="execution(public * com.mycompany.springwebapp.controller.Ch14Controller.afterThrowing*(..))",
			throwing="e")
	//pointcut 메소드가 에러가 날 때 예외를 출력시키는데 잘 사용하지 않는다.
	public void weavingMethod(Throwable e) {
		log.info("afterThrowing 실행");
		log.info("예외 메시지 : " + e.getMessage());
	}
}
