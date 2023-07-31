package com.mycompany.springwebapp.service;

import javax.annotation.Resource;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch13MemberDao;
import com.mycompany.springwebapp.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;
//주입을 직접 이름을 줄 수 있다.
@Service/*("boardServiceImpl")*/
@Slf4j
public class Ch13MemberServiceImpl implements Ch13MemberService{
	@Resource
	private Ch13MemberDao memberDao;
	
	@Override
	public JoinResult join(Ch13Member member) {
		Ch13Member dbMember = memberDao.selectByMid(member.getMid());
		if(dbMember != null) {
			return JoinResult.FAIL_DUPLICATED_MID;
		} 
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			member.setMpassword(passwordEncoder.encode(member.getMpassword()));
			
			memberDao.insert(member);
			return JoinResult.SUCCESS;
	}

	@Override
	public LoginResult login(Ch13Member member) {
		Ch13Member dbMember = memberDao.selectByMid(member.getMid());
		if(dbMember == null) {
			return LoginResult.FAIL_MID;
		}
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		if(passwordEncoder.matches(member.getMpassword(), dbMember.getMpassword())) {
			if(dbMember.isMenabled()) {
				return LoginResult.SUCCESS;
			}  else {
				return LoginResult.FAIL_MENABLED;
			}
		} else {
			return LoginResult.FAIL_MPASSWORD;
		}
	}

	@Override
	public void logout(String mid) {
		// 만약 DB에 로그인 정보가 저장되어있다면, 삭제하는 코드를 작성한다.
	}

	@Override
	public void modify(Ch13Member member) {
		memberDao.update(member);
	}

	@Override
	public void unjoin(String mid) {
		Ch13Member member = memberDao.selectByMid(mid);
		member.setMenabled(false);
		memberDao.update(member);
		//modify(member);
	}

	@Override
	public Ch13Member getMember(String mid) {
		Ch13Member member = memberDao.selectByMid(mid);
		return member;
	}
	
}
