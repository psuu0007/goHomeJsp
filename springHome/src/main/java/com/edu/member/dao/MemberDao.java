package com.edu.member.dao;

import java.util.List;
import java.util.Map;

import com.edu.member.vo.MemberVo;

public interface MemberDao {

	// 게시물
	public List<MemberVo> memberSelectList(
			String searchOption, String keyword, int start, int end);
	public MemberVo memberExist(Map<String, Object> paramMap);
	public int memberInsertOne(MemberVo memberVo);
	public MemberVo memberSelectOne(int no);
	public int memberUpdateOne(MemberVo memberVo);
	public int memberDelete(int no);
	
	// 페이징
	public int memberSelectTotalCount(Map<String, String> map);

	// 파일첨부
	public void insertFile(Map<String, Object> map);
	public List<Map<String, Object>> fileSelectList(int no);
	public int fileDelete(int no);
	public Map<String, Object> fileSelectStoredFileName(int no);
}
