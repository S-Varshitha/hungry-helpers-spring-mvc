<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
<%@ include file="admin_navbar.jsp"%>
	<table border="2">
		<tr>
			<th>Product Id</th>
			<th>Product Name</th>
			<th>Product description</th>
			<th>Cost</th>
			<th>Type</th>
			<th>Food Type</th>
			<th>Offer</th>
			<th>Image</th>
			<th>Edit Item</th>
			<th>Delete Item</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.description}</td>
				<td>${product.cost}</td>
				<td>${product.type}</td>
				<td>${product.food_type}</td>
				<td>${product.offer}</td>
				<td><img src="${product.imgLink}" height="50" width="50" /></td>
				<td><a href="editproduct?id=${product.id}">Edit Item</a></td>
				<td><a href="deleteproduct?id=${product.id}">Delete Item</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>