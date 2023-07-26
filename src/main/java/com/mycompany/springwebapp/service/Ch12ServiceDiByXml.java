package com.mycompany.springwebapp.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.mycompany.springwebapp.dao.Ch12DaoByXml1;
import com.mycompany.springwebapp.dao.Ch12DaoByXml2;
import com.mycompany.springwebapp.dao.Ch12DaoByXml3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12ServiceDiByXml {
	//필드
	private Ch12DaoByXml1 daoXml1;
	private Ch12DaoByXml2 daoXml2;
	private Ch12DaoByXml3 daoXml3;
	
	//생성자
	public Ch12ServiceDiByXml() {}
	
	//생성자
	public Ch12ServiceDiByXml(Ch12DaoByXml1 daoByXml1) {
		log.info("Ch12DaoByXml1 실행");
		this.daoXml1 = daoByXml1;
	}
	
	//Setter
	public void setCh12DaoByXml1(Ch12DaoByXml1 daoByXml1) {
		log.info("Ch12DaoByXml1 실행");
		this.daoXml1 = daoByXml1;
	}
	public void setCh12DaoByXml2(Ch12DaoByXml2 daoByXml2) {
		log.info("Ch12DaoByXml2 실행");
		this.daoXml2 = daoByXml2;
	}
	public void setCh12DaoByXml3(Ch12DaoByXml3 daoByXml3) {
		log.info("Ch12DaoByXml3 실행");
		this.daoXml3 = daoByXml3;
	}
	public void setCollection1 (List<String> list) {
		log.info("setCollection1 실행");
		for(String item : list) {
			log.info(item);
		}
	}
	
	public void setCollection2(Set set) {
		log.info("setColletion2 실행");
		log.info("아이템 수 " + set.size());
	}
	
	public void setCollection3(Map map) {
		log.info("setColletion3 실행");
		log.info("아이템 수 " + map.size());
	}
	//Properties는 key value가 전부다 문자열이다.
	public void setCollection4(Properties props) {
		log.info("setColletion4 실행");
		log.info("아이템 수 " + props.size());
	}
	//Instance Method
	public void method() {
		daoXml1.method();
		daoXml2.method();
		daoXml3.method();
	}
}
