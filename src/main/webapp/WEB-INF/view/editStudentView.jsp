<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa thông tin</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<jsp:include page="menu.jsp"></jsp:include>

	<h2 style="padding-left: 500px">Sửa thông tin sinh viên</h2>
	<p style="color: red; padding-left: 100px">${error}</p>
	<c:if test="${not empty selectedStudent }">
		<form action="${pageContext.request.contextPath}/edit-student" method="post">
			<input type="hidden" name="code" value="${selectedStudent.studentID}">
			Họ và tên:<br>
			<input type="text" name="fullName" value="${selectedStudent.fullName}"><br> 
			Ngày sinh:<br>
			<input type="text" name="dob" value="${selectedStudent.dob}"><br>
			Giới tính:<br>
			<c:if test="${selectedStudent.gender == true}">
				<input name="gender" type="radio" value="1" checked="checked">Nam
				<input name="gender" type="radio" value="0">Nữ<br>
			</c:if>
			
			<c:if test="${selectedStudent.gender == false}">
				<input name="gender" type="radio" value="1" >Nam
				<input name="gender" type="radio" value="0" checked="checked">Nữ<br>
			</c:if>
			 Địa chỉ:<br>
			<input type="text" name="address" value="${selectedStudent.address}"><br>
			Reset password:<input name="resetPassword" type="radio" value="1" /><br>
			<br> 
			<input type="submit" value="OK">
			<a style="padding-left: 50px" href="${pageContext.request.contextPath}/student-list">Huỷ</a>
		</form>
	</c:if>
</body>
</html>