package com.kh.spring.member.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	//로그인서비스
	Member loginMember(Member m);
	
	//id check를 위한 서비스
	int idCheck(String checkId);
}
