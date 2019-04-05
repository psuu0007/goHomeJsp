<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	table{
		border-collapse: collapse;
	}
	
	table, tr, td{
		border: 1px solid black;
	}
</style>
<title>회원 목록</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/Header.jsp" />

	<h1>회원목록</h1>
	<div>
		<a href="add.do">신규 회원</a>
	</div>
	<br />

<!--  var=변수명  items=목록데이터 begin=시작인덱스 end=종료인덱스 -->
	<table>
		<tr>
			<td>번호</td><td>회원이름</td>
			<td>이메일</td><td>가입일</td>
			<td></td>
		</tr>
	<c:forEach var="memberVo" items="${memberList}">
		<tr>
			<td>${memberVo.no}</td>
			<td>
				<a href='./listOne.do?no=${memberVo.no}'>${memberVo.name}</a>
			</td>
			<td>${memberVo.email}</td>
			<td>
				<fmt:formatDate value="${memberVo.createDate}"
					pattern="yyyy년MM월dd일 hh시mm분"/>
			</td>
			<td>
				<a href='./deleteCtr.do?mno=${memberVo.no}'>[삭제]</a>
			</td>
		</tr>
	</c:forEach>
	</table>

	<jsp:include page="/WEB-INF/views/common/paging.jsp">
		<jsp:param value="${pagingMap}" name="pagingMap"/>
	</jsp:include>

	<form action="./list.do" id="pagingForm" method="post">
		<input type="hidden" id="curPage" name="curPage" 
			value="${pagingMap.memberPaging.curPage}">
	</form>
	
	<form action="./list.do" method="get">
		<select name="searchOption">
			<option value="title">제목</option>  <!-- 이메일 -->
			<option value="content">내용</option>  <!-- 이름 -->
		</select>
		<input type="text" name="keyword" value="">
		<input type="submit" value="검색">
	</form>
	
	<jsp:include page="/WEB-INF/views/Tail.jsp" />
	
</body>
</html>