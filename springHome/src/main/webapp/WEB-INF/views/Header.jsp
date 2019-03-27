<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="background-color: #000086; color: #ffffff; 
	height: 20px; padding: 5px;">

	SPMS(Simple Project Management System)
	<span style="float:right;">
		<c:if test="${sessionScope._memberVo_ != null}">
			${sessionScope._memberVo_.name}
			<a href="${pageContext.request.contextPath}/auth/logout.do"	style="color:white;">
				로그아웃
			</a>
		</c:if>
		<c:if test="${sessionScope._memberVo_ == null}">
			게스트1
			<a href="${pageContext.request.contextPath}/auth/logout.do"	style="color:white;">
				나가기
			</a>
		</c:if>
		
	</span>

</div>