package com.edu.member.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.member.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class MemberServiceImplTest {

	@Autowired
	MemberServiceImpl memberServiceImpl; 
	
	@Test
	public void testMemberSelectList() {
		
		List<MemberVo> list = memberServiceImpl.memberSelectList();
		
//		Assert.assertEquals("s1@test.com", list.get(0).getEmail());
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getEmail());
			
//			Assert.assertEquals("s1@test.com", list.get(i).getEmail());
			
		}
		
	}
	
}
