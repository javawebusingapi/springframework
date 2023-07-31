package com.mycompany.springwebapp.dto;

import java.sql.Blob;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Ch13Board {
	private int bno;
	private String btitle;
	private String bcontent;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bdate;
	private String mid;
	private int bhitcount;
	
	//Client -> Controller 보내는 데이터 받기위한 Field
	private MultipartFile battach;
	//Controller -> Service -> DAO -> DB 로 전달하기 위한 필드
	private String battachoname;
	private String battachsname;
	//방법1 : 서버 파일 시스템이 파일로 저장
	private String battachtype;
	//방법2 : DB에 Blob타입으로 저장
	//Byte[] <- MyBatis -> Blob  (MyBatis에서 알아서 처리해준다)
	private byte[] battachdata;
}
