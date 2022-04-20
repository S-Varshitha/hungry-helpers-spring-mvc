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
<body>
	<form:form action="createoffer" modelAttribute="offer" method="post">
		<table>
			<tr>
				<td><form:label path="coupenCode">Enter CouponCode</form:label></td>
				<td><form:input path="coupenCode" /></td>
			</tr>
			<tr>
				<td><form:label path="discount">Discount</form:label></td>
				<td><form:input path="discount"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create Offer">
			</tr>
		</table>
	</form:form>
</body>
</html>