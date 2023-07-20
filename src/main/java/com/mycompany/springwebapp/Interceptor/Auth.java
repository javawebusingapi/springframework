package com.mycompany.springwebapp.Interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Rentention : 언제 적용 할 건지 (RUNTIME : 실행 시)
//Target : 어디에 적용시킬 건지 (METHOD : 메소드에 적용시킬 어노테이션)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {
	
	//Role이라는 enum을 만든다 (USER와 ADMIN만 사용 가능)
	public enum Role {USER,ADMIN};
	
	//Role을 쓸건데 default값은 USER로
	public Role value() default Role.USER;
}
