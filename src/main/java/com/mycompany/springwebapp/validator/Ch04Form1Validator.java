package com.mycompany.springwebapp.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springwebapp.dto.Ch04Form1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//Form1에서 넘어온거만 유효성 검사를 한다는 클래스명(Ch04Form1Validator)
public class Ch04Form1Validator implements Validator{
	@Override
	//유효성 검사를 지원하냐 안하냐를 확인하는 메소드
	public boolean supports(Class<?> clazz) {
		log.info("supports 실행");
						//B(clazz)가 A(Ch04Form1)타입으로 대입이 가능한가? true가 나와야만 유효성 검사가 가능
		boolean check = Ch04Form1.class.isAssignableFrom(clazz);
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("validate 실행");
		Ch04Form1 ch04Form1 = (Ch04Form1) target;
		
		//param1 검사
		String param1 = ch04Form1.getParam1();
		if(param1 == null || param1.equals("")) {
											//다국어 지원을 위한 에러 코드
			errors.rejectValue("param1", "errors.form1.required", "필수입력(D)");
		} else if (param1.length() < 8) {
			errors.rejectValue("param1", "errors.form1.minlength", new Object[] {8},"최소 8자 입력(D)");
		} else if (param1.length() > 15) {
			errors.rejectValue("param1", "errors.form1.maxlength", new Object[] {15},"최대 15자 입력(D)");
		}
		
		//param2 검사
		String param2 = ch04Form1.getParam2();
		if(param2 == null || param2.equals("")) {
			//다국어 지원을 위한 에러 코드
			errors.rejectValue("param2", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "(010|011)-[0-9]{3,4}-[0-9]{4}";
			boolean result = Pattern.matches(regExp, param2);
			if (result == false) {
				errors.rejectValue("param2", "errors.form1.format", "전화번호 형식에 안맞는다(D)");
			}
		}
		
		String param3 = ch04Form1.getParam3();
		if(param3 == null || param3.equals("")) {
			errors.rejectValue("param3", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)";
			boolean result = Pattern.matches(regExp, param3);
			if(result == false) {
				errors.rejectValue("param3", "errors.form1.format", "이메일 형식에 안맞는다(D)");
			}
		}
	}
}
