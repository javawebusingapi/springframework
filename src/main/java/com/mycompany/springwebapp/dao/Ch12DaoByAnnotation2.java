package com.mycompany.springwebapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class Ch12DaoByAnnotation2 {
	public Ch12DaoByAnnotation2() {
		log.info("Ch12DaoByAnnotation2 실행");
	}
	
	public void method() {
		log.info("2 method 실행");
	}
}
