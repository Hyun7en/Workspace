package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	/*
	 * Spring에서 파라미터를 받는 방법
	 * 1. HttpServletRequest를 활용해서 전달받기(jsp/servlet방식)
	 * 해당 메소드의 매개변수로 HttpServletRequest를 작성해두면
	 * 스프링컨테이너가 해당 메소드를 호출시 자동으로 객체생성해서 매게변수로 주입해준다.
	 */

//	@RequestMapping("login.me")
//	public String loginMember(HttpServletRequest request) {
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		
//		System.out.println("userId : " + userId);
//		System.out.println("userPwd : " + userPwd);
//		
//		return "main";
//		///WEB-INF/views/main.jsp
//	}
//	
	@RequestMapping("login.me")
	public String loginMember(String userId, String userPwd) {
		
		System.out.println("userId : " + userId);
		System.out.println("userPwd : " + userPwd);
		
		return "main";
		///WEB-INF/views/main.jsp
	}
}
