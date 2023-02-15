<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<h2 style="padding-left: 500px">Thông tin sinh viên</h2>
	<p style="color: red; padding-left: 100px">${error}</p>	
	
	<form action="${pageContext.request.contextPath}/search-student" method="post">
		Tìm kiếm sv: <input type="search" name="searchStudent"
			placeholder="Nhập mã sv"> <input type="submit"><br>
		<br>
	</form>

	<c:if test="${not empty student}">
		<table style="width: 100%">
			<tr>
				<th>Mã sv</th>
				<th>Tên</th>
				<th>Ngày sinh</th>
				<th>Giới tính</th>
				<th>Địa chỉ</th>
				<th>Sửa</th>
				<th>Xoá</th>
			</tr>

			<tr>
				<td>${student.studentID}</td>
				<td>${student.fullName}</td>
				<td>${student.dob}</td>

				<c:choose>
					<c:when test="${student.gender == true}">
						<td><c:out value="Nam"></c:out></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="Nữ"></c:out></td>
					</c:otherwise>
				</c:choose>

				<td>${student.address}</td>

			</tr>
		</table>
	</c:if>
</body>
</html>