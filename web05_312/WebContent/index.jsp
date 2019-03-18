<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	pageContext.setAttribute("sdfsdf", 3423);
	request.setAttribute("fdsf", 24);
	session.setAttribute("dsfsd", 432423);
	application.setAttribute("fsdsfddsfdsf", 32432);

%>
	<a href="./member/list">회원목록페이지 이동</a>
</body>
</html>