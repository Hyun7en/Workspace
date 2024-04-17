package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.vo.Member;
import com.kh.spring.member.service.MemberService;

@Controller
public class MemberController {
	
//	private MemberService memberService = new MemeberServiceImpl();
	/*
	 * 기존 객체 생성 방식
	 * 객체간 결합도가 높아짐(소스코드 수정이 일어날 경우 하나하나 전부 다 바꿔줘야한다.)
	 * 서비스가 동시에 매우 많은 횟수요청이 될 경우 그만큼 객체가 생성된다.
	 * 
	 * Spring의 DI(Dependency Injection)를 이용한 방식
	 * 객체를 생성해서 주입해준다.
	 * new라는 객체생성 키워드없이 @Autowired라는 어노테이션만으로 사용해야한다.
	 */
	
	@Autowired
	private MemberService memberService;
	
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
	
//	/*
//	 * 2. @RequestParam 어노테이션을 이용하는 방법
//	 * request.getParameter("키")로 벨류를 추출하는 역할을 대신해주는 어노테이션
//	 * value속성의 벨류로 jsp에서 작성했던 name속성값을 담으면 알아서 해당 매개변수로 받아올 수 있다.
//	 * 만약, 넘어온 값이 비어있는 형태라면 defaultValue속성으로 기본값을 지정할 수 있다.
//	 * 
//	 * @RequestParam 생략이 가능하다.
//	 */
//	@RequestMapping("login.me")
//	public String loginMember(@RequestParam(value="userId", defaultValue="testId") String id,@RequestParam String userPwd) {
//		
//		System.out.println("userId : " + id);
//		System.out.println("userPwd : " + userPwd);
//		
//		return "main";
//		///WEB-INF/views/main.jsp
//	}
	
	/*
	 * 3. 커맨드 객체 방식
	 * 
	 * 해당 메소드의 매개변수로
	 * 요청시 전닭밧을 담고자하는 VO 클래스의 타입을 세팅 후
	 * 요청시 전달값의 키값(jsp의 name속성값)을 vo클래스에 담고자하는 필드명으로 작성
	 */
	
//	@RequestMapping("login.me")
//	public String loginMember(Member m) {
//		
//		System.out.println("userId : " + m.getUserId());
//		System.out.println("userPwd : " + m.getUserPwd());
//		
//		Member loginUser = memberService.loginMember(m);
//		
//		if(loginUser == null) {
//			System.out.println("로그인 실패");
//		} else {
//			System.out.println("로그인 성공");
//		}
//		return "main";
//		///WEB-INF/views/main.jsp
//	}
	
	
	/*
	 * 요청처리 후 응답데이터를 담고 응답페이지로 포워딩 또는 url재요청 처리하는 방법
	 * 
	 * 1. 스프링에서 제공하는 Model객체를 이용하는 방법
	 * 포워딩할 응답뷰로 전달하고자하는 데이터를 맵형식(k-v)으로 담을 수 있는 영역
	 * Model객체는 reqesutScope
	 * request.setAttribute() -> model.addAttribute()
	 */
	
//	@RequestMapping("login.me")
//	public String loginMember(Member m, Model model, HttpSession session) {	
//		Member loginUser = memberService.loginMember(m);
//		
//		if(loginUser == null) { //로그인 실패 => 에러문구를 requestScope에 담고 에러페이지로 포워딩
//			model.addAttribute("errorMsg", "로그인실패");
//			
//			// /WEB-INF/views/common/errorPage.jsp
//			return "/common/errorPage";
//		} else { // 로그인 성공 => sessionScope에 로그인유저 담아서 메인으로 url재요청
//			session.setAttribute("loginUser", loginUser);
//			return "redirect:/";
//		}
//	}
	
	//2. 스프링에서 제공하는 ModelAndView객체사용
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, ModelAndView mv, HttpSession session) {	
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser == null) { //로그인 실패 => 에러문구를 requestScope에 담고 에러페이지로 포워딩
			//model.addAttribute("errorMsg", "로그인실패");
			mv.addObject("errorMsg", "로그인실패");
			
			// /WEB-INF/views/common/errorPage.jsp
			//return "/common/errorPage";
			mv.setViewName("common/errorPage");
		} else { // 로그인 성공 => sessionScope에 로그인유저 담아서 메인으로 url재요청
			session.setAttribute("loginUser", loginUser);
			//return "redirect:/";
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
}
