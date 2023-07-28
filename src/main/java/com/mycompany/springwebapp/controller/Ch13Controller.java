package com.mycompany.springwebapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dao.Ch13MemberDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13") //이 컨트롤러는 ch13이 포함이된다면 선택이 된다. 뒤의 메소드는 ch13을 빼버려도 된다.
public class Ch13Controller {
	
	@Resource
	private Ch13BoardService boardService;
	
	/*@Resource
	//인터페이스기 때문에 구현객체가 필요하지만 Mybatis가 구현객체를 알아서 만들어줌
	private Ch13BoardDao boardDao;*/
	
	@Resource
	private Ch13MemberDao memberDao;
	
	@RequestMapping("/content")
	public String content() {
		log.info("ch13 content 실행");
		return "ch13/content";
	}
	
	@GetMapping("/insertboard")
	public String insertboard (){
		log.info("insertboard 실행");
		
		/*for(int i=1; i<=10000; i++) {
			Ch13Board board = new Ch13Board();
			board.setBtitle("글이 너무 많아"+i);
			board.setBcontent("내용도 너무 많아"+i);
			board.setMid("user");
			
			//boardDaoOld.insert(board);
			boardDao.insert(board);
			
			//실제로 저장된 bno
			log.info("저장된 bno : " + board.getBno());
		}*/
		Ch13Board board = new Ch13Board();
		board.setBtitle("졸리다");
		board.setBcontent("그냥 졸리다");
		board.setMid("user");
		
		//boardDaoOld.insert(board);
		boardService.write(board);
		
		//실제로 저장된 bno
		log.info("저장된 bno : " + board.getBno());
		
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/getboardList")
	public String getboardList(){
		log.info("getboardList 실행");
		int totalBoardNums = boardService.getTotalBoardNum();
		Ch13Pager pager = new Ch13Pager(10,5,totalBoardNums,1);
		//List<Ch13Board> list = boardDaoOld.selectByPage(pager);
		List<Ch13Board> list = boardService.getList(pager);
		for(Ch13Board board : list) {
			log.info(board.toString());
		}
		return "redirect:/ch13/content";
	}
	
	
	@GetMapping("/updateboard")
	public String updateboard() {
		log.info("updateboard 실행");
		//Select를 먼저 해준 다음, update를 해야함 (이것을 안하면 update시 다른 컬럼은 null이기 떄문에 필요한 bno만 수정)
		//Ch13Board board = boardDaoOld.selectByBno(2);
		Ch13Board board = boardService.getBoard(2);
		board.setBtitle("수정한 졸리다");
		board.setBcontent("수정한 그냥 졸리다");
		board.setMid("user");
		
		//boardDaoOld.updateByBno(board);
		boardService.modify(board);
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/deleteboard")
	public String deleteboard() {
		log.info("deleteboard 실행");
		//boardDaoOld.deleteByBno(bno);
		int bno = 3;
		boardService.remove(bno);
		return "redirect:/ch13/content";
	}
	
	
	
}