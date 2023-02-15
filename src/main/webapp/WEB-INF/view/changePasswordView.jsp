<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đổi mật khẩu</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<jsp:include page="menu.jsp"></jsp:include>

	<h2 style="padding-left: 500px">Đổi mật khẩu</h2>
	<p style="color: red; padding-left: 100px">${error}</p>

	<form action="${pageContext.request.contextPath}/change-password"
		method="post">
		Mật khẩu cũ:<br> <input type="password" name="oldPassword"><br>
		Mật khẩu mới:<br> <input type="password" name="newPassword"><br>
		Gõ lại mật khẩu mới:<br> <input type="password" name="duplicatePassword"><br> <br> 
		
		<input type="submit" value="OK"> 
		<a style="padding-left: 50px" href="${pageContext.request.contextPath}/student-info">Huỷ</a>
	</form>
</body>
</html>