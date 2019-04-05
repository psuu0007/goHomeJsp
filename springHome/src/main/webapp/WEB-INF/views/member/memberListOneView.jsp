<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 조회</title>

<script type="text/javascript" 
	src="/springHome/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	window.onload = function(){
		var memberNameInputObj = 
			document.getElementById('memberName');
		
		memberNameInputObj.style.backgroundColor = '#E7E7E7';
	}
	
	function pageMoveListFnc(){
		location.href = "list.do";
	}
		
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/Header.jsp" />
	
	<h1>회원정보</h1>

	<form action="./update.do" method="get">
		<input type="hidden" name='no' value='${memberVo.no}'>
		이름: <input type="text" name='name' id='memberName' value='${memberVo.name}' 
				readonly="readonly"><br>
		이메일: <input type="text" name="email" 
			value='${memberVo.email}' readonly="readonly"><br>
			
		첨부파일: 
		<c:choose>
			<c:when test="${empty fileList}">
				첨부파일이 없습니다.<br>
			</c:when>
			<c:otherwise>
				<c:forEach var="row" items="${fileList}">
<%-- 				<input type="hidden" class="files" value="${row.IDX}"> --%>
				${row.ORIGINAL_FILE_NAME} (${row.FILE_SIZE}kb)<br>
				<img alt="image not found" src="<c:url value='/img/${row.STORED_FILE_NAME}'/>"/><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		가입일: <fmt:formatDate value="${memberVo.createDate}" 
				pattern="yyyy-MM-dd"/><br>
		<input type="submit" value="수정하기">
		<input type="button" value="이전페이지" 
				onclick="pageMoveListFnc();">
	</form>

	<%-- <form action="../common/fileDownload.do" method="post">
		<input type="hidden" id='filePno' name='filePno' value='${fileList[0].PNO}'>
	</form> --%>

	<jsp:include page="/WEB-INF/views/Tail.jsp"/>

</body>
</html>