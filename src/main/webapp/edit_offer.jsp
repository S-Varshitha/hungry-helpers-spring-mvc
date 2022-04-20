<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ include file="admin_navbar.jsp"%>
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
<body>
	<form:form action="updateoffer" modelAttribute="offer" method="get">
		<table>
			<tr>
				<td><form:label path="id">Offer ID</form:label></td>
				<td><form:input path="id" readonly="true"/></td>
			</tr>
			<tr>
				<td><form:label path="coupenCode">Enter CouponCode</form:label></td>
				<td><form:input path="coupenCode" /></td>
			</tr>
			<tr>
				<td><form:label path="status">Status</form:label></td>
				<td><form:input path="status" /></td>
			</tr>
			<tr>
				<td><form:label path="discount">Discount</form:label></td>
				<td><form:input path="discount"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update Offer">
			</tr>
		</table>
	</form:form>
</body>
</html>