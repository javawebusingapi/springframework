package com.mycompany.springwebapp.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springwebapp.dto.Ch04Form1;
import com.mycompany.springwebapp.dto.Ch04Form2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//Form2에서 넘어온거만 유효성 검사를 한다는 클래스명(Ch04Form2Validator)
public class Ch04Form2Validator implements Validator{
	@Override
	//유효성 검사를 지원하냐 안하냐를 확인하는 메소드
	public boolean supports(Class<?> clazz) {
		log.info("supports 실행");
						//B(clazz)가 A(Ch04Form2)타입으로 대입이 가능한가? true가 나와야만 유효성 검사가 가능
		boolean check = Ch04Form2.class.isAssignableFrom(clazz);
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("validate 실행");
		Ch04Form2 ch04Form2 = (Ch04Form2) target;
	
		//param1 검사
		String param21 = ch04Form2.getParam21();
		if(param21 == null || param21.equals("")) {
			log.info("1");
			errors.rejectValue("param21", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-8][0-9]{6}";
			boolean result = Pattern.matches(regExp, param21);
			if(result == false) {
				errors.rejectValue("param21", "errors.form1.format", "주민번호 형식에 안맞는다(D)");
			}
		}
		
		//param2 검사
		String param22 = ch04Form2.getParam22();
		if(param22 == null || param22.equals("")) {
			log.info("2");
			//다국어 지원을 위한 에러 코드
			errors.rejectValue("param22", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "\\d{4}\\/(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])";
			boolean result = Pattern.matches(regExp, param22);
			if (result == false) {
				errors.rejectValue("param22", "errors.form1.format", "생년월일 형식에 안맞는다(D)");
			}
		}
		
		String param23 = ch04Form2.getParam23();
		if(param23 == null || param23.equals("")) {
			log.info("3");
											//다국어 지원을 위한 에러 코드
			errors.rejectValue("param23", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "^[a-zA-Z0-9]{8,15}$";
			boolean result = Pattern.matches(regExp, param23);
			if (result == true || param23.length() < 8) {
				errors.rejectValue("param23", "errors.form1.minlength", new Object[] {8},"최소 8자 입력(D)");
			} else if (result == true || param23.length() > 10) {
				errors.rejectValue("param23", "errors.form1.maxlength", new Object[] {15},"최대 10자 입력(D)");
			}
		}
	}
}
