package com.mycompany.springwebapp.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mycompany.springwebapp.Interceptor.Auth;
import com.mycompany.springwebapp.Interceptor.Auth.Role;
import com.mycompany.springwebapp.dto.Ch02Dto;
import com.mycompany.springwebapp.dto.Ch02FileInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch02") //이 컨트롤러는 ch02이 포함이된다면 선택이 된다. 뒤의 메소드는 ch02을 빼버려도 된다.
public class Ch02Controller {
	//@Slf4j를 쓰지 않는 경우
	//private static final Logger log = LoggerFactory.getILoggerFactory(Ch01Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch02 content 실행");
		return "ch02/content";
	}
	
	//위의 방법과 같은 방법 (view 이름을 String으로 바꾼것과 ModelAndView로 리턴한 것)(잘 안쓴다.)
	/*@RequestMapping("/content")
	public ModelAndView content() {
		log.info("ch02 content 실행");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ch02/content");
		//mav.addObject("data", "홍길동");
		return mav;
	}*/
	
	
	@RequestMapping(value="/method", method=RequestMethod.GET) //주의점 : jsp에서 쓴 bkind와 bkind의 변수명이 같아야한다 (bno또한 동일)
	public String method1(String bkind, int bno) {
		log.info("bkind: " + bkind);
		log.info("bno : " + bno);
		return "ch02/content";
	}
	
	@RequestMapping(value="/method", method=RequestMethod.POST) //주의점 : jsp에서 쓴 bkind와 bkind의 변수명이 같아야한다 (bno또한 동일)
	public String method2(String bkind, int bno) {
		log.info("bkind: " + bkind);
		log.info("bno : " + bno);
		return "ch02/content";
	}
	
	/*@RequestMapping(value="/method", method=RequestMethod.PUT) 
	public void method3(@RequestBody String json, HttpServletResponse response) throws Exception{
		JSONObject jsonObject = new JSONObject(json);
		String bkind = jsonObject.getString("bkind");
		int bno = jsonObject.getInt("bno");
		log.info("bkind: " + bkind);
		log.info("bno : " + bno);
		
		JSONObject root = new JSONObject();
		root.put("result", "success");
		String responseJson = root.toString();	//{"result":"success"} 
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();
		pw.close();
	}*/
	
	@RequestMapping(value="/method", method=RequestMethod.PUT) 
	public void method3(@RequestBody Ch02Dto dto, HttpServletResponse response) throws Exception{
		log.info("bkind: " + dto.getBkind());
		log.info("bno : " + dto.getBno());
		
		JSONObject root = new JSONObject();
		root.put("result", "success");
		String responseJson = root.toString();	//{"result":"success"} 
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/method", method=RequestMethod.DELETE) 
	public void method4(@RequestBody String json, HttpServletResponse response) throws Exception{
		JSONObject jsonObject = new JSONObject(json);
		int bno = jsonObject.getInt("bno");
		log.info("bno : " + bno);
		
		JSONObject root = new JSONObject();
		root.put("result", "success");
		String responseJson = root.toString();	//{"result":"success"} 
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/ajax1")
	public String ajax1() {
		
		return "ch02/fragmentHtml";
	}
	
	@GetMapping("/ajax2")
	public void ajax2(HttpServletResponse response) throws Exception{
		
		JSONObject root = new JSONObject();
		root.put("fileName", "face.png");
		String responseJson = root.toString();	//{"result":"success"} 
		
		//직접 JSON응답을 생성
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();	
		pw.close();
	}
	
	@GetMapping(value="/ajax3", produces="application/json; charset=UTF-8")
	@ResponseBody //리턴되는 값을 응답 본문에 바로 싣기
	public String ajax3() {
		JSONObject root = new JSONObject();
		root.put("fileName", "chamgo.PNG");
		String responseJson = root.toString();	//{"result":"success"} 
		return responseJson;
	}
	
	@GetMapping(value="/ajax4", produces="application/json; charset=UTF-8")
	@ResponseBody //리턴값을 JSON으로 변환하여 응답 본문에 바로 싣기, jackson-databind 라이브러리가 필요함
	public Ch02FileInfo ajax4() {
		Ch02FileInfo fileInfo = new Ch02FileInfo();
		//필드 이름이 속성으로, 필드 값이 값으로
		fileInfo.setFileName("체리9.jpg");
		return fileInfo;
	}
	
	@GetMapping("/fileDownload")
	public void fileDownload (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String fileName = "chamgo.PNG";
		String filePath = "/resources/images/image/"+fileName;
		filePath = request.getServletContext().getRealPath(filePath);
		log.info("filePath : " + filePath);
				
		//응답 헤드에 Content-Type 추가 : 본문에 뭐가있는지를 브라우저에게 알려주어야함
		String mimeType = request.getServletContext().getMimeType(filePath);
		response.setContentType(mimeType);
		
		
		//응답 헤더에 한글 이름의 파일명을 ISO-8859-1 문자셋으로 인코딩해서 추가
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE
			fileName = URLEncoder.encode(fileName,"UTF-8");
			log.info(fileName);
		} else {
			//Chrome, Edge, FireFox, Safari
			//HTTP 헤더에는 한글이 들어갈 수 없으므로 UTF-8을 ISO-8859-1의 형식으로 변환한다.
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		//해석 할 수 있는 파일이여도 파일로 다운로드를 받기 위한 코드
		response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
		
		//응답 본문에 파일 데이터 싣기
		OutputStream os = response.getOutputStream();
		Path path = Paths.get(filePath);
		Files.copy(path,os);
		os.flush();
		os.close();
	}
	
	@RequestMapping("/filterAndInterceptor")
	@Auth(/*value=*/Role.ADMIN) //@Auth(Role.ADMIN)이 없다면 adminMethod가 요청을 받으면 무조건 실행되지만, @Auth(Role.ADMIN)이 있다면 ADMIN권한을 가진 사람만 요청을 받아 실행
	public String adminMethod() {
		log.info("실행");
		return "ch02/adminPage";
	}
	
}
