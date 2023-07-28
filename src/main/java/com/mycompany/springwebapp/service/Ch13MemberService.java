package com.mycompany.springwebapp.service;

import com.mycompany.springwebapp.dto.Ch13Member;

public interface Ch13MemberService {
	public enum LoginResult {
			SUCCESS,
			FAIL_MID,
			FAIL_MPASSWORD,
			FAIL_MENABLED
	}
	
	public void join(Ch13Member member);        	//회원 가입
	public LoginResult login(Ch13Member member);	//로그인
	public void logout(String mid);    		  		//로그아웃
	public void modify(Ch13Member member);	    	//회원 정보 수정
	public void unjoin(String mid);	            	//회원 탈퇴
	public Ch13Member getMember(String mid);        //멤버정보 가져오기
}
