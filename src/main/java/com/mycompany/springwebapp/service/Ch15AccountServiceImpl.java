package com.mycompany.springwebapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.springwebapp.dao.Ch15AccountDao;
import com.mycompany.springwebapp.dto.Ch15Account;
import com.mycompany.springwebapp.exception.Ch15NotFoundAccountException;

import lombok.extern.slf4j.Slf4j;
//주입을 직접 이름을 줄 수 있다.
@Service/*("boardServiceImpl")*/
@Slf4j
public class Ch15AccountServiceImpl implements Ch15AccountService{
	
	@Resource
	private Ch15AccountDao accountDao;
	
	@Override
	public List<Ch15Account> getAccounts() {
		List<Ch15Account> list = accountDao.selectAll();
		return list;
	}

	@Override
	//Transactional이 없다면 입금계좌만 존재하고 출금계좌는 존재하지 않아도 에러는 뜨지만 입금계좌에 돈은 들어가는 상황이 나오기 떄문에 달아줘야한다.
	@Transactional
	public void transfer(int fromAno, int toAno, int amount) {
		//출금
		Ch15Account fromAccount = accountDao.selectByAno(fromAno);
		if(fromAccount == null) {
			throw new Ch15NotFoundAccountException("출금 계좌가 없음");
		}
		fromAccount.setBalance(fromAccount.getBalance()- amount);
		accountDao.updateBalance(fromAccount); //DML
	
		//입금
		Ch15Account toAccount = accountDao.selectByAno(toAno);
		if(toAccount == null) {
			throw new Ch15NotFoundAccountException("입금 계좌가 없음");
		}
		toAccount.setBalance(toAccount.getBalance() + amount);
		accountDao.updateBalance(toAccount); //DML
	}	
}
