<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Spring</title>
<script type="text/javascript">
	function memberMoveFnc(){
		var formObj = document.getElementById('simpleForm');
		
		formObj.submit();
	}
</script>
</head>
<body>
	Hello Spring Projects..
	<form action="member/list.do" id='simpleForm' method="get">
	
		<input type="button"  
			value="회원목록 화면으로 이동" onclick="memberMoveFnc();">
	</form>
</body>
</html>