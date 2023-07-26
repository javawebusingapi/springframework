package com.mycompany.springwebapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class Ch12DaoByXml3 {
	public Ch12DaoByXml3() {
		log.info("Ch12DaoByXml3 실행");
	}
	
	public void method() {
		log.info("3 method 실행");
	}
}
