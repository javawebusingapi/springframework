package com.mycompany.springwebapp.Interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Rentention : 언제 적용 할 건지 (RUNTIME : 실행 시)
//Target : 어디에 적용시킬 건지 (METHOD : 메소드에 적용시킬 어노테이션)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Login {
	public String value() default "Login";
}
