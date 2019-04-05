package com.edu.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.vo.MemberVo;

public interface MemberService {

	public List<MemberVo> memberSelectList(int start, int end);
	public MemberVo memberExist(Map<String, Object> paramMap);
	public void memberInsertOne(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	public Map<String, Object> memberSelectOne(int no);
	public int memberUpdateOne(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest, int fileIdx) throws Exception;
	public int memberDelete(int no) throws Exception;
	
	public int memberSelectTotalCount();
}
