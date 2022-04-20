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
<%@include file="staff_navbar.jsp" %>
<%
	String name=(String)session.getAttribute("name");
	String role=(String)session.getAttribute("role");
	if(name==null && role==null){
		response.sendRedirect("home.jsp");
	}
	if(role!=null && !role.equals("staff")){
		response.sendRedirect("home.jsp");
	}
%>
<table border="2">
	<tr>
		<th>Order ID</th>
		<th>Item ID</th>
		<th>Item Name</th>
		<th>Item Cost</th>
		<th>Item Quantity</th>
		<th>Item Description</th>
	</tr>
	<c:forEach items="${itemsList}" var="item">
		<tr>
			<td>${item.mcOrder.order_id}</td>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.cost}</td>
			<td>${item.quantity}</td>
			<td>${item.description}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>