package com.mycompany.springwebapp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch12DaoByAnnotation1;
import com.mycompany.springwebapp.dao.Ch12DaoByAnnotation2;
import com.mycompany.springwebapp.dao.Ch12DaoByAnnotation3;

import lombok.extern.slf4j.Slf4j;
//관리객체(필수)
@Service	//자동적으로 객체 생성 , 무조건 기본생성자로 객체 생성 -> 매개변수 주지 말것, (조건을 충족하면 사용 가능)
@Slf4j
public class Ch12ServiceDiByAnnotation {
	//필드
	@Resource
	private Ch12DaoByAnnotation1 daoAnnotation1;
	private Ch12DaoByAnnotation2 daoAnnotation2;
	private Ch12DaoByAnnotation3 daoAnnotation3;	
	
	//생성자
	public Ch12ServiceDiByAnnotation() {
		log.info("Ch12ServiceDiByAnnotation 생성자 실행");
	}
	
	//Setter
	public void setCh12DaoByAnnotation1(Ch12DaoByAnnotation1 daoByAnnotation1) {
		log.info("Ch12DaoByAnnotation1 실행");
		this.daoAnnotation1 = daoByAnnotation1;
	}
	@Autowired
	public void setCh12DaoByAnnotation2(Ch12DaoByAnnotation2 daoByAnnotation2) {
		log.info("Ch12DaoByAnnotation2 실행");
		this.daoAnnotation2 = daoByAnnotation2;
	}
	@Autowired
	public void setCh12DaoByAnnotation3(Ch12DaoByAnnotation3 daoByAnnotation3) {
		log.info("Ch12DaoByAnnotation3 실행");
		this.daoAnnotation3 = daoByAnnotation3;
	}
	//Instance Method
	public void method() {
		daoAnnotation1.method();
		daoAnnotation2.method();
		daoAnnotation3.method();
	}
}
