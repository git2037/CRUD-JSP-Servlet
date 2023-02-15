<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

li {
	float: left;
}

li a {
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 20px;
}

li a:hover {
	background-color: #999999;
}
</style>
</head>
<body>

	<ul>
		<li><a class="active"
			href="${pageContext.request.contextPath}/home">Home</a></li>
		<c:choose>
			<c:when test="${loginedUser.compareTo('admin') == 0}">
				<li><a href="${pageContext.request.contextPath}/student-list">Danh sách sv</a></li>
			</c:when>
			
			<c:when test="${loginedUser.startsWith('sv')}">
				<li><a href="${pageContext.request.contextPath}/student-info">Thông tin sv</a></li>
			</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${loginedUser != null}">
				<li><a href="${pageContext.request.contextPath}/logout">Thoát</a></li>
			</c:when>

			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	
</body>
</html>





