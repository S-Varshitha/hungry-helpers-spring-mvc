<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Datails</title>
</head>
<body>
<%
	String name = (String) session.getAttribute("name");
	String role = (String) session.getAttribute("role");
	if (name == null && role == null) {
		response.sendRedirect("home.jsp");
	}
	if (role != null && !role.equals("admin")) {
		response.sendRedirect("home.jsp");
	}
	%>
<%@ include file="admin_navbar.jsp" %>
	<table border="2">
		<tr>
			<th>User Id</th>
			<th>User Name</th>
			<th>User Phone Number</th>
			<th>User Email</th>
			<th>User Password</th>
			<th>User Role</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td>${user.role}</td>
				<td><a href="edituser?id=${user.id}">Edit</a></td>
				<td><a href="deleteuser?id=${user.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>