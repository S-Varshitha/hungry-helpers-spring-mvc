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
	String name=(String)session.getAttribute("name");
	String role=(String)session.getAttribute("role");
	if(name==null && role==null){
		response.sendRedirect("home.jsp");
	}
	if(role!=null && !role.equals("chef")){
		response.sendRedirect("home.jsp");
	}
%>
<%@include file="chef_navbar.jsp" %>
	<tr>
		<table border="2">
			<tr>
				<th>McOrders Id</th>
				<th>Customer Name</th>
				<th>Update Status</th>
			</tr>
			<c:forEach items="${mcOrders}" var="mcOrders">
				<tr>
					<td>${mcOrders.order_id}</td>
					<td>${mcOrders.cust_name}</td>
					<td><a href="updatestatuschef?id=${mcOrders.order_id}">${mcOrders.status}</a>
					</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>