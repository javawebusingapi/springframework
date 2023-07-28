package com.mycompany.springwebapp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;
//구식(직접 작성해서 쓰는것)
@Repository //관리객체(주입되기 가능)
@Slf4j
public class Ch13BoardDaoOldImpl implements Ch13BoardDaoOld {
	@Resource
	private SqlSessionTemplate sst;
	public int insert(Ch13Board board) {
		/*
		 * com.mycompany.springwebapp.dao.mybatis.Ch13BoardDao : Maaper MXL 선택
		 * .insert : Mapper XML 안에 선언된 ID
		 * 리턴 값 : 실제 테이블이 변경된 행의 수
		 * */
		int rows = sst.insert(
				"com.mycompany.springwebapp.dao.Ch13BoardDao.insert",board);
		return rows;
	}
	public List<Ch13Board> selectByPage(Ch13Pager pager) {
		List<Ch13Board> list = sst.selectList(
				"com.mycompany.springwebapp.dao.Ch13BoardDao.selectByPage");
		for(Ch13Board board : list) {
			log.info(board.toString());
		}
		return list;
	}
	
	public int count() {
		int count = sst.selectOne("com.mycompany.springwebapp.dao.Ch13BoardDao.count");
		return count;
	}
	
	public Ch13Board selectByBno(int bno) {
		Ch13Board board = sst.selectOne("com.mycompany.springwebapp.dao.Ch13BoardDao.selectByBno",bno);
		return board;
	}
	
	public int updateByBno(Ch13Board board) {
		int rows = sst.update("com.mycompany.springwebapp.dao.Ch13BoardDao.updateByBno", board);
		return rows;
	}
	
	public int deleteByBno(int bno) {
		int rows = sst.delete("com.mycompany.springwebapp.dao.Ch13BoardDao.deleteByBno", bno);
		return rows;
	}
}