<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
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
<%@ include file="admin_navbar.jsp"%>
<body>
	<table border="2">
		<tr>
			<th>Offer ID</th>
			<th>Coupon code</th>
			<th>Status</th>
			<th>Date</th>
			<th>Discount</th>
			<th>Edit Offer</th>
			<th>Delete Offer</th>
		</tr>
		<c:forEach items="${offers}" var="offer">
			<tr>
				<td>${offer.id}</td>
				<td>${offer.coupenCode}</td>
				<td>${offer.status}</td>
				<td>${offer.date}</td>
				<td>${offer.discount}</td>
				<td><a href="editoffer?id=${offer.id}">Edit</a></td>
				<td><a href="deleteoffer?id=${offer.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>