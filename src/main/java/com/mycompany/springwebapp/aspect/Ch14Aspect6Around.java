package com.mycompany.springwebapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect6Around {

	@Around("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.around*(..))")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		//전처리 공통코드
		log.info("around 전처리");
		
		//proceed는 Object를 리턴
		Object result = joinPoint.proceed(); //핵심 로직 (비즈니스 메소드), around*(..) 메소드 호출
		
		//후처리 공통코드
		log.info("around 후처리");
		
		
		return result;
	}
}
