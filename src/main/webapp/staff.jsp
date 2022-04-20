<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<form action="createorder">
	<table>
		<tr>
			<td>Name</td>
			<td><input type="text" name="customername"></td>
		</tr>
		<tr>
			<td>Phone Number</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>Coupon Code</td>
			<td><input type="text" name="couponcode"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Create Orders"></td>
		</tr>
	</table>
</form>
</body>
</html>