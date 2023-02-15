<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<div style="margin: 100px 400px 0px 350px; border: 2px outset black">	
		<h1 style="padding-left: 150px">Đăng nhập</h1>
		<p style="color: red; padding-left: 100px">${error}</p>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<input type="hidden" name="servletPath" value="${servletPath}">
			<p style="padding-left: 100px">
				UserName: <input type="text" name="userName" placeholder="UserName"><br><br> 
				Password: <input type="password" name="password"placeholder="password"><br><br> 
				<input style="padding-left: 10px" type="submit" value="Log In"><br>
		</form>
	</div>
</body>
</html>