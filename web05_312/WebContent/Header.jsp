<%@page import="com.tg.member.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDto memberDto = (MemberDto)session.getAttribute("member");
// 	System.out.println("어떤 값일까??????????? " + request.getContextPath());
%>

<div style="background-color: #000086; color: #ffffff; 
	height: 20px; padding: 5px;">

	SPMS(Simple Project Management System)
	<span style="float:right;">
		<%=memberDto.getName()%>
		<a href="<%=request.getContextPath()%>/auth/logout"	style="color:white;">
			로그아웃
		</a>
	</span>

</div>