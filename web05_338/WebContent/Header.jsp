<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="background-color: #000086; color: #ffffff; 
	height: 20px; padding: 5px;">

	SPMS(Simple Project Management System)
	<span style="float:right;">
		${sessionScope.member.name}
		<a href="<%=request.getContextPath()%>/auth/logout"	style="color:white;">
			로그아웃
		</a>
	</span>

</div>