package com.mycompany.springwebapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch13BoardDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13BoardServiceImpl implements Ch13BoardService{
	@Resource
	private Ch13BoardDao boardDao;

	@Override
	public void write(Ch13Board board) {
		boardDao.insert(board);
		//boardDaoOld.insert(board);
	}

	@Override
	public void remove(int bno) {
		boardDao.deleteByBno(bno);
		//boardDaoOld.deleteByBno(bno);
	}

	@Override
	public void modify(Ch13Board board) {
		boardDao.updateByBno(board);
		//boardDaoOld.updateByBno(board);
	}

	@Override
	public List<Ch13Board> getList(Ch13Pager pager) {
		List<Ch13Board> list = boardDao.selectByPage(pager);
		//List<Ch13Board> list = boardDaoOld.selectByPage(pager);
		return list;
	}

	@Override
	public Ch13Board getBoard(int bno) {
		Ch13Board board = boardDao.selectByBno(bno); 
		//Ch13Board board = boardDaoOld.selectByBno(bno); 
		return board;
	}

	@Override
	public void addHitcount(int bno) {
		Ch13Board board = boardDao.selectByBno(bno);
		board.setBhitcount(board.getBhitcount()+1);
		boardDao.updateByBno(board);
		
		/*Ch13Board board = boardDaoOld.selectByBno(bno);
		board.setBhitcount(board.getBhitcount()+1);
		boardDaoOld.updateByBno(board);*/
	}

	@Override
	public int getTotalBoardNum() {
		int totalBoardNum = boardDao.count();
		return totalBoardNum;
	}
	
	
}
