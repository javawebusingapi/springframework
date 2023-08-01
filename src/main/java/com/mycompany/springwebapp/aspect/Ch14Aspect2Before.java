package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@Order(1)
public class Ch14Aspect2Before {
																//*는 메소드(모든 메소드), (..)는 안에 뭐가 들어가던 상관 없음을 의미
	@Before("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.before*(..))")
	public void weavingMethod() {
		log.info("before2 실행");
	}
}
