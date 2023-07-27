package com.mycompany.springwebapp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.springwebapp.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

//구식(직접 작성해서 쓰는것)
@Repository //관리객체(주입되기 가능)
@Slf4j
public class Ch13BoardDaoOld {
	
	@Resource
	private SqlSessionTemplate sst;
	
	public void insert(Ch13Board board) {
		sst.insert(
				"com.mycompany.springwebapp.dao.mybatis.Ch13BoardDao.insert",
				board
				);
	}
	
	public List<Ch13Board> selectAll() {
		List<Ch13Board> list = sst.selectList(
				"com.mycompany.springwebapp.dao.mybatis.Ch13BoardDao.selectAll"
				
				);
		for(Ch13Board board : list) {
			log.info(board.toString());
		}
		return list;
	}
	
	public void updateByBno() {
		List<Ch13Board> list = selectAll();
		Ch13Board board = list.get(0);
		board.setBtitle("변경된 제목도 졸리다");
		board.setBcontent("변경된 내용도 겁나 졸리다");
		
		sst.update("com.mycompany.springwebapp.dao.mybatis.Ch13BoardDao.updateByBno", board);
	}
	
	public void deleteByBno() {
		int bno = 1;
		sst.delete("com.mycompany.springwebapp.dao.mybatis.Ch13BoardDao.deleteByBno", bno);
	}
}
