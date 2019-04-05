package com.edu.member.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.edu.member.vo.MemberVo;

public interface MemberDao {

	public List<MemberVo> memberSelectList(int start, int end);
	public MemberVo memberExist(Map<String, Object> paramMap);
	public int memberInsertOne(MemberVo memberVo);
	public MemberVo memberSelectOne(int no);
	public int memberUpdateOne(MemberVo memberVo);
	public int memberDelete(int no);
	
	public int memberSelectTotalCount();
	public void insertFile(Map<String, Object> map);
	public List<Map<String, Object>> fileSelectList(int no);
	
	public int fileDelete(int no);
	public Map<String, Object> fileSelectStoredFileName(int no);
}
