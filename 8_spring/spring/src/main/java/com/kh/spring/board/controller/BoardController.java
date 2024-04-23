package com.kh.spring.board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.board.service.BoardService;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		int boardCount = boardService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(boardCount, currentPage, 10, 5);
		ArrayList<Board> list = boardService.selectList(pi);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		return "board/boardListView";
	}
	
	@RequestMapping(value = "detail.bo")
	public String selectBoard(int bno, Model model) {
		
		int result = boardService.increaseCount(bno);
		
		if (result > 0) {
			Board b = boardService.selectBoard(bno);
			model.addAttribute("b", b);
			
			return "board/boardDetailView";
			
		} else {
			model.addAttribute("errorMsg", "게시글 조회 실패");
			return "common/errorPage";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "rlist.bo", produces="application/json; charset-UTF-8")
	public String ajaxSelectReplyList(int bno) {
		ArrayList<Reply> list = boardService.selectReply(bno);
		
		return new Gson().toJson(list);
	}
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board b, MultipartFile upfile) {
		System.out.println(b);
		System.out.println(upfile);
		
		//전달된 파일이 있을 경우 => 파일이름 변경 => 서버에 저장 => 원본명, 서버업로드된 경로를 b객체에 담기
		if(!upfile.getOriginalFilename().equals("")) {
			String changeName = saveFile(upfile);
			
		}
		return "main";
	}
	
	//실제 넘어온 파일의 이름을 변경해서 서버에 저장하는 메소드
	public String saveFile(MultipartFile upfile) {
		String originName = upfile.getOriginalFilename();
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}
