package com.mycompany.springwebapp.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springwebapp.dto.Ch04Form2;
import com.mycompany.springwebapp.dto.Ch04Join;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//Form2에서 넘어온거만 유효성 검사를 한다는 클래스명(Ch04Form2Validator)
public class Ch04JoinValidator implements Validator{
	@Override
	//유효성 검사를 지원하냐 안하냐를 확인하는 메소드
	public boolean supports(Class<?> clazz) {
		log.info("supports 실행");
						//B(clazz)가 A(Ch04Form2)타입으로 대입이 가능한가? true가 나와야만 유효성 검사가 가능
		boolean check = Ch04Join.class.isAssignableFrom(clazz);
		return check;
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("validate 실행");
		Ch04Join ch04Join = (Ch04Join) target;
	
		//param1 검사
		String mid = ch04Join.getMid();
		if(mid == null || mid.equals("")) {
			log.info("1");
			errors.rejectValue("mid", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "[a-z]+[a-z0-9]{5,19}";
			boolean result = Pattern.matches(regExp, mid);
			if(result == false) {
				errors.rejectValue("mid", "errors.form1.format", "아이디 형식에 맞지않는다.(D)");
			}
		}
		
		//param2 검사
		String mpassword = ch04Join.getMpassword();
		if(mpassword == null || mpassword.equals("")) {
			log.info("2");
			//다국어 지원을 위한 에러 코드
			errors.rejectValue("mpassword", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}";
			boolean result = Pattern.matches(regExp, mpassword);
			if (result == false) {
				errors.rejectValue("mpassword", "errors.form1.format", "비밀번호 형식에 맞지 않는다.(D)");
			}
		}
		
		String memail = ch04Join.getMemail();
		if(memail == null || memail.equals("")) {
			log.info("3");
			errors.rejectValue("memail", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)";
			boolean result = Pattern.matches(regExp, memail);
			if(result == false) {
				errors.rejectValue("memail", "errors.form1.format", "이메일 형식에 안맞는다(D)");
			}
		}
		
		String mtel = ch04Join.getMtel();
		if(mtel == null || mtel.equals("")) {
			log.info("4");
			//다국어 지원을 위한 에러 코드
			errors.rejectValue("mtel", "errors.form1.required", "필수입력(D)");
		} else {
			String regExp = "(010|011)-[0-9]{3,4}-[0-9]{4}";
			boolean result = Pattern.matches(regExp, mtel);
			if (result == false) {
				errors.rejectValue("mtel", "errors.form1.format", "전화번호 형식에 안맞는다(D)");
			}
		}
	}
}
