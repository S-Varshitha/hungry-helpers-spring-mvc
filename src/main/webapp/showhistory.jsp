<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
<%@include file="admin_navbar.jsp" %>
<table border="2">
		<tr>
			<th>McOrders Id</th>
			<th>Customer Name</th>
			<th>Date</th>
			<th>Time</th>
			<th>Total cost</th>
			<th>View Item</th>
		</tr>
		<c:forEach items="${mcOrders}" var="mcOrders">
			<tr>
				<td>${mcOrders.order_id}</td>
				<td>${mcOrders.cust_name}</td>
				<td>${mcOrders.date}</td>
				<td>${mcOrders.time}</td>
				<td>${mcOrders.totalCost}</td>
				<td><a href="getitemsbyid?orderId=${mcOrders.order_id}">View</a></td>
				</tr>
		</c:forEach>
	</table>
	<form action="clearhistory">
	<input type="submit" value="Clear History">
	</form>

</body>
</html>