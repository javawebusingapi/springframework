package com.mycompany.springwebapp.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springwebapp.dto.Ch08Member;
import com.mycompany.springwebapp.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch13LoginCheckInterceptor implements HandlerInterceptor{
	//전처리 할 때 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//log.info("Interceptor ADMIN 전처리 실행");
		
		HandlerMethod hm = (HandlerMethod) handler;
		Login login = hm.getMethodAnnotation(Login.class);
		
		//@Login이 붙어있다면
		if(login != null) {
			Ch13Member member = (Ch13Member) request.getSession().getAttribute("ch13Login");
			//로그인이 되었다면
			if(member != null) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath()+"/ch13/login");
				return false;
			}
		//@Login이 붙어있지 않다면
		} else {
			return true;
		}
		
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
