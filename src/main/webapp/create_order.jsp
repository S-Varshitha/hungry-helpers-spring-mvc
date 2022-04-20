<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
			<th>Product Id</th>
			<th>Product Name</th>
			<th>Product description</th>
			<th>Cost</th>
			<th>Type</th>
			<th>Food Type</th>
			<th>Offer</th>
			<th>Image</th>
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
				<td><img src="${product.imgLink}" height="50" width="50"/></td>
			</tr>
		</c:forEach>
	</table>



	<form:form action="updateorder" method="post" modelAttribute="item">
		<table>
			<tr>
				<td><form:label path="id">Product ID : </form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			
			<tr>
				<td><form:label path="quantity">Quantity : </form:label></td>
				<td><form:input path="quantity" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add">
			</tr>
		</table>
	</form:form>
	<a href="conformorder">Confirm Order</a>
</body>
</html>