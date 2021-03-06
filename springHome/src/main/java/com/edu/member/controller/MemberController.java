package com.edu.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.service.MemberService;
import com.edu.member.vo.MemberVo;
import com.edu.util.Paging;

@Controller
public class MemberController {

	private final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	// 회원 목록 조회 화면으로
	@RequestMapping(value="/member/list.do"
			, method= {RequestMethod.GET, RequestMethod.POST})
	public String memberList(
			@RequestParam(defaultValue ="1") int curPage,
			@RequestParam(defaultValue ="title") String searchOption,
			@RequestParam(defaultValue ="") String keyword,
			Model model) {
		
		log.debug("Welcome MemberController memberList! : {}"
				, curPage);
		log.debug(": {}", searchOption);
		log.debug(": {}", keyword);
		
		int totalCount = 
				memberService.memberSelectTotalCount(searchOption, keyword);
		
		Paging memberPaging = new Paging(totalCount, curPage);
		int start = memberPaging.getPageBegin();
		int end = memberPaging.getPageEnd();

		List<MemberVo> memberList = 
				memberService.memberSelectList(
						searchOption, keyword, start, end);
		
		Map<String, Object> pagingMap = new HashMap<>();
		pagingMap.put("totalCount", totalCount);
		pagingMap.put("memberPaging", memberPaging);

		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingMap", pagingMap);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchOption", searchOption);
		
		return "member/memberListView";
	}

	@RequestMapping(value = "/member/listOne.do")
	public String memberListOne(int no, Model model) {
		log.debug("Welcome memberListOne enter! - {}", no);

//		MemberVo memberVo = memberService.memberSelectOne(no);
		Map<String, Object> map = memberService.memberSelectOne(no);

		MemberVo memberVo = (MemberVo) map.get("memberVo");
		List<Map<String, Object>> fileList = (List<Map<String, Object>>)map.get("fileList");

		model.addAttribute("memberVo", memberVo);
		model.addAttribute("fileList", fileList);
		

		return "member/memberListOneView";
	}

	@RequestMapping(value = "/auth/login.do", method = RequestMethod.GET)
	public String login(HttpSession session, Model model) {
		log.debug("Welcome MemberController login 페이지 이동! ");

		return "auth/loginForm";
	}

	@RequestMapping(value = "/auth/loginCtr.do", method = RequestMethod.POST)
	public String loginCtr(String email, String password, HttpSession session, Model model) {
		log.debug("Welcome MemberController loginCtr! " + email + ", " + password);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		paramMap.put("pwd", password);

		MemberVo memberVo = memberService.memberExist(paramMap);

		String viewUrl = "";
		if (memberVo != null) {
			// 회원이 존재한다면 세션에 담고
			// 회원 전체 조회 페이지로 이동
			session.setAttribute("_memberVo_", memberVo);

			viewUrl = "redirect:/member/list.do";
		} else {
			viewUrl = "/auth/loginFail";
		}

		return viewUrl;
	}

	@RequestMapping(value = "/auth/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		log.debug("Welcome MemberController logout 페이지 이동! ");

		// 세션의 객체들 파기
		session.invalidate();

		return "redirect:/auth/login.do";
	}

	@RequestMapping(value = "/member/add.do", method = RequestMethod.GET)
	public String memberAdd(Model model) {
		log.debug("Welcome MemberController memberAdd 페이지 이동! ");

		return "member/memberForm";
	}

	@RequestMapping(value = "/member/addCtr.do", method = RequestMethod.POST)
	public String memberAdd(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest, Model model) {
		log.trace("Welcome MemberController memberAdd 신규등록 처리! " + memberVo);

		try {
			memberService.memberInsertOne(memberVo, multipartHttpServletRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("아 오류 처리;");
			e.printStackTrace();
		}

		return "redirect:/member/list.do";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/member/update.do")
	public String memberUpdate(int no, Model model) {
		log.debug("Welcome memberUpdate enter! - {}", no);

		Map<String, Object> map = memberService.memberSelectOne(no);

		MemberVo memberVo = (MemberVo) map.get("memberVo");
		List<Map<String, Object>> fileList = (List<Map<String, Object>>)map.get("fileList");

		model.addAttribute("memberVo", memberVo);
		model.addAttribute("fileList", fileList);

		return "member/memberUpdateForm";
	}

	@RequestMapping(value = "/member/updateCtr.do", method = RequestMethod.POST)
	public String memberUpdateCtr(HttpSession session, MemberVo memberVo
			, @RequestParam(value="fileIdx", defaultValue="-1") int fileIdx
			, MultipartHttpServletRequest multipartHttpServletRequest, Model model) {
		log.debug("Welcome MemberController memberUpdateCtr {} :: {}", memberVo, fileIdx);

		int resultNum = 0;
		
		try {
			resultNum = memberService.memberUpdateOne(memberVo, multipartHttpServletRequest, fileIdx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		// 데이터베이스에서 회원정보가 수정이 됬는지 여부
		if (resultNum > 0) {

			MemberVo sessionMemberVo = (MemberVo) session.getAttribute("_memberVo_");
			// 세션에 객체가 존재하는지 여부
			if (sessionMemberVo != null) {
				// 세션의 값과 새로운 값이 일치하는지 여부
				// 홍길동 ㄴㅇㄹㄴㅇ
				// s1@test.com ㄴㅇㄹ33@
				// 1111 2222
				if (sessionMemberVo.getNo() == memberVo.getNo()) {
					MemberVo newMemberVo = new MemberVo();

					newMemberVo.setNo(memberVo.getNo());
					newMemberVo.setEmail(memberVo.getEmail());
					newMemberVo.setName(memberVo.getName());

					session.removeAttribute("_memberVo_");

					session.setAttribute("_memberVo_", newMemberVo);
				}
			}
		}

		return "common/successPage";
	}

	
	@RequestMapping(value = "/member/deleteCtr.do", method = RequestMethod.GET)
	public String memberDelete(@RequestParam(value = "mno") int no, Model model) {
		log.debug("Welcome MemberController memberDelete" + " 회원삭제 처리! - {}", no);

		try {
			memberService.memberDelete(no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/member/list.do";
	}
}
