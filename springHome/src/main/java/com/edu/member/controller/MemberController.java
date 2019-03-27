package com.edu.member.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.member.service.MemberService;
import com.edu.member.vo.MemberVo;


@Controller
public class MemberController {

	private static final Logger log = 
			LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	// 회원 목록 조회 화면으로
	@RequestMapping(value="/member/list.do", method=RequestMethod.GET)
	public String memberList(Model model) {
		
		log.debug("Welcome MemberController enter! ");
		
		List<MemberVo> memberList = memberService.memberSelectList();
		
		model.addAttribute("memberList", memberList);
		
		return "member/memberListView";
	}
	
}
