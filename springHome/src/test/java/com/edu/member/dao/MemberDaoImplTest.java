package com.edu.member.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.member.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class MemberDaoImplTest {
	
	@Autowired
	public MemberDaoImpl memberDaoImpl;
	
	@Test
	@Ignore
	public void testMemberSelectList() {
		// TODO Auto-generated method stub
//		List<MemberVo> memberList = memberDaoImpl.memberSelectList();	
//		
//		Assert.assertNotNull(memberList);
//		Assert.assertEquals("LMS", memberList.get(0).getName());
//		Assert.assertTrue(0 < memberList.size());
		
	}
	
}
