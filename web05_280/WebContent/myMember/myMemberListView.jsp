<%@page import="com.tg.member.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비번까지 전부</title>
</head>
<body>
	
	<h1>내가 만든 회원목록 전체 조회</h1>
	<div>
		<a href="#">신규 회원</a>
	</div>
	<br/>
	
	<%
		ArrayList<MemberDto> memberList = 
			(ArrayList<MemberDto>)request.getAttribute("myMemberList");
	
		for(MemberDto memberDto : memberList){
	%>
	
<!-- 	여기가 내가 추가함 -->
	
<!-- 	저 표현식만 없으면 전부 html코드로 인식함 -->
	
	<%=memberDto.getNo()%>,
	<a href='./update?no=<%=memberDto.getNo()%>'><%=memberDto.getName()%></a>,
	<%=memberDto.getEmail()%>,
	<%=memberDto.getCreateDate()%>
	<a href='./delete?no=<%=memberDto.getNo()%>'>[삭제]</a>
	수정한 날짜: <%=memberDto.getModifiedDate()%>
	<br>
	
	<%
		}
	%>
	
</body>
</html>