package com.edu.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.vo.MemberVo;

public interface MemberService {

	public List<MemberVo> memberSelectList(
			String searchOption, String keyword, int start, int end);
	public MemberVo memberExist(Map<String, Object> paramMap);
	public void memberInsertOne(MemberVo memberVo, 
			MultipartHttpServletRequest multipartHttpServletRequest);
	public MemberVo memberSelectOne(int no);
	public int memberUpdateOne(MemberVo memberVo);
	public int memberDelete(int no);
	
	public int memberSelectTotalCount(String searchOption, String keyword);
}
