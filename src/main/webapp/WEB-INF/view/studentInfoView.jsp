<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin sinh viên</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<jsp:include page="menu.jsp"></jsp:include>

	<h2 style="padding-left: 500px">Thông tin sinh viên</h2>
	<p style="color: red; padding-left: 100px">${error}</p>

	<c:if test="${not empty AcctuallyStudent}">		
			Họ và tên:<br>
		<input type="text" name="fullName"
			value="${AcctuallyStudent.fullName}" readonly="readonly">
		<br> 
			Ngày sinh:<br>
		<input type="text" name="dob" value="${AcctuallyStudent.dob}" readonly="readonly">
		<br>
			Giới tính:<br>
		<c:if test="${AcctuallyStudent.gender == true}">
			<input name="gender" type="text" value="Nam" readonly="readonly"><br>
		</c:if>

		<c:if test="${AcctuallyStudent.gender == false}">
			<input name="gender" type="text" value="Nữ" readonly="readonly"><br>
		</c:if>
			 Địa chỉ:<br>
		<input type="text" name="address" value="${AcctuallyStudent.address}" readonly="readonly">
		<br>

		<a style="padding-left: 50px"
			href="${pageContext.request.contextPath}/change-password">Đổi mật
			khẩu</a>

	</c:if>
</body>
</html>