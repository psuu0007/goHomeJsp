<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

	<h2>사용자 로그인</h2>
	<div>
		<form action="loginCtr.do" method="post">
			<table>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value=""></td>
				</tr>
				<tr>
					<td>암호</td>
					<td><input type="password" name='password'></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>