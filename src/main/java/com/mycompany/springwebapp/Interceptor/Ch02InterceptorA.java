package com.mycompany.springwebapp.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch02InterceptorA implements HandlerInterceptor{
	//전처리 할 때 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("Interceptor 전처리 실행");
		
		//컨트롤러의 요청매핑 메소드는 실행
		//Return true가 되어야 Controller 실행(아래있는 메소드들도 실행) , False가 되면  Controller 미실행
		return true;
		
		//컨트롤러의 요청매핑 메소드는 실행되지 않음
		//throw new RuntimeException();
		//또는
		//return false;
	}
	
	//후처리 할 떄 실행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//log.info("Intetceptor 후처리 실행");
	}
	
	
	//응답이 바로 가기 직전에 실행(JSP까지 다 실행)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//log.info("Intetceptor 응답 직전");
	}
}
