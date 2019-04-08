package com.edu.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.member.dao.MemberDao;
import com.edu.member.vo.MemberVo;
import com.edu.util.FileUtils;

@Service
public class MemberServiceImpl implements MemberService {

	private final Logger log = 
			LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	public MemberDao memberDao;

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Override
	public List<MemberVo> memberSelectList(
			String searchOption, String keyword, int start, int end) {

		return memberDao.memberSelectList(searchOption, keyword, start, end);
	}

	@Override
	public MemberVo memberExist(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub

		return memberDao.memberExist(paramMap);
	}

	@Transactional
	@Override
	public void memberInsertOne(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest)
			throws Exception {
		// TODO Auto-generated method stub
		memberDao.memberInsertOne(memberVo);

		int parentSeq = memberVo.getNo();
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(parentSeq, multipartHttpServletRequest);

		log.debug("MemberServiceImpl memberInsertOne enter!! {}", list);
		
		for (int i = 0; i < list.size(); i++) {
			memberDao.insertFile(list.get(i));
		}
	}

	@Override
	public Map<String, Object> memberSelectOne(int no) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();

		MemberVo memberVo = memberDao.memberSelectOne(no);
		resultMap.put("memberVo", memberVo);

		List<Map<String, Object>> fileList = memberDao.fileSelectList(no);
		resultMap.put("fileList", fileList);

		return resultMap;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public int memberUpdateOne(MemberVo memberVo, MultipartHttpServletRequest multipartHttpServletRequest, int fileIdx)
			throws Exception {
		// TODO Auto-generated method stub
		int resultNum = 0;
		
		try {
			
			resultNum = memberDao.memberUpdateOne(memberVo);

			int parentSeq = memberVo.getNo();
			Map<String, Object> tempFileMap = memberDao.fileSelectStoredFileName(parentSeq);

			List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(parentSeq, multipartHttpServletRequest);
			
			// 오로지 하나만 관리
			if (list.isEmpty() == false) {
				if(tempFileMap != null) {
					fileUtils.parseUpdateFileInfo(tempFileMap);
					memberDao.fileDelete(parentSeq);
				}
				
				for (Map<String, Object> map : list) {
					memberDao.insertFile(map);
				}

			}else if(fileIdx == -1) {
				if(tempFileMap != null) {
					memberDao.fileDelete(parentSeq);
					fileUtils.parseUpdateFileInfo(tempFileMap);
				}
			}
			
		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
			
		return resultNum;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public int memberDelete(int no) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> tempFileMap = null;
		tempFileMap = memberDao.fileSelectStoredFileName(no);
		
		int result = 0;
		if(tempFileMap != null) {
			 result = memberDao.fileDelete(no);	
		}
		
		if(result != 0) {
			fileUtils.parseUpdateFileInfo(tempFileMap);
		}
		
		return memberDao.memberDelete(no);
	}

	@Override
	public int memberSelectTotalCount(String searchOption, String keyword) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return memberDao.memberSelectTotalCount(map);
	}

}
