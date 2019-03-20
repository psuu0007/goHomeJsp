<%@page import="com.tg.SubjectDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
table {
	border-collapse: collapse;
}

table, tr, td {
	border: 1px solid black;
}
</style>
<title>일단 주소 쉽게 보는 화면</title>
</head>
<%
	SubjectDto[] subjectDtoArr = new SubjectDto[3];

	subjectDtoArr[0] = new SubjectDto(1, "국어", 40);
	subjectDtoArr[1] = new SubjectDto(2, "영어", 50);
	subjectDtoArr[2] = new SubjectDto(3, "수학", 100);

	request.setAttribute("subjectDtoArr", subjectDtoArr);
%>
<body>
	<table>
		<tr>
			<td>번호</td>
			<td>과목명</td>
			<td>점수</td>
		</tr>
		<c:forEach var="subjectDto" items="${subjectDtoArr}">
			<tr>
				<td>${subjectDto.num}</td>
				<td>${subjectDto.name}</td>
				<td>${subjectDto.score}</td>
			</tr>
		</c:forEach>
	</table>




</body>
</html>