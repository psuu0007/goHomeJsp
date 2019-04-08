package com.edu.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.vo.MemberVo;

/**
 * @since	2019. 4. 9.
 * @author	Administrator
 * <PRE>
 * ----------------------------
 * 개정이력
 * 2019. 4. 9. Administrator : 최초작성
 * </PRE>
 */
public interface MemberService {

	public List<MemberVo> memberSelectList(
			String searchOption, String keyword, int start, int end);
	public MemberVo memberExist(Map<String, Object> paramMap);
	
	public void memberInsertOne(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;
	public Map<String, Object> memberSelectOne(int no);
	
	/**
	 * @param memberVo
	 * @param multipartHttpServletRequest
	 * @param fileIdx	: 첨부파일 존재여부(-1=없음, 그 이외 값=존재)  
	 * @throws Exception
	 * @Author	:	Administrator
	 * @Date	:	2019. 4. 9.
	 * @Method Name	:	memberUpdateOne
	 * @return	:	int
	 */
	public int memberUpdateOne(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest, int fileIdx) throws Exception;
	public int memberDelete(int no) throws Exception;

	public int memberSelectTotalCount(String searchOption, String keyword);
}
