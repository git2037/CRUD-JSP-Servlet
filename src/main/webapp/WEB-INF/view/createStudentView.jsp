<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sinh viên</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<jsp:include page="menu.jsp"></jsp:include>	
	
	<h2 style="padding-left: 500px">Thêm sinh viên</h2>
	<p style="color: red; padding-left: 100px">${error}</p>
	
	<form action="${pageContext.request.contextPath}/create-student" method="post">
		Mã sv:<br><input type="text" name="studentID" placeholder="StudentID"><br>
		Họ và tên:<br><input type="text" name="fullName" placeholder="FullName"><br>
		Ngày sinh:<br><input type="text" name="dob" placeholder="dd/mm/yyyy"><br>
		Giới tính:<br><input name="gender" type="radio" value="1" checked="checked">Nam
					<input name="gender" type="radio" value="0" >Nữ<br>
		Địa chỉ:<br><input type="text" name="address" placeholder="Address"><br>
		Password:<br><input type="text" name="password" placeholder="Password"><br><br>
		<input type="submit" value="OK">
		<a style="padding-left: 50px" href="${pageContext.request.contextPath}/student-list">Huỷ</a>
	</form>

</body>
</html>