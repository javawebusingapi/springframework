package com.mycompany.springwebapp.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ControllerAdvice
public class Ch15NotFoundAccountException extends RuntimeException {
	public Ch15NotFoundAccountException() {
		
	}
	
	public Ch15NotFoundAccountException (String message) {
		super(message);
	}
}
