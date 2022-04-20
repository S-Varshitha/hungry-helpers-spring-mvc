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
<%@include file="staff_navbar.jsp" %>
<body>
	<h1>Items You Ordered</h1>
	<table border="2">
		<tr>
			<th>Name</th>
			<th>Quantity</th>
			<th>Cost</th>
		</tr>
		<c:forEach items="${items}" var="items">
			<tr>
				<td>${items.name}</td>
				<td>${items.quantity}</td>
				<td>${items.cost}</td>
			</tr>
		</c:forEach>
	</table>
	<h1>Billing Details</h1>
	<form:form action="staff.jsp" modelAttribute="mcOrder">
		<table>
			<tr>
				<td><form:label path="order_id">ID : </form:label></td>
				<td><form:input path="order_id" value="" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="cust_name">Customer Name : </form:label></td>
				<td><form:input path="cust_name" value="" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="totalCost">Cost : </form:label>
				<td><form:input path="totalCost" readonly="true" />
			</tr>

			<tr>
				<td><input type="submit" value="Print">
			</tr>
		</table>
	</form:form>
</body>
</html>