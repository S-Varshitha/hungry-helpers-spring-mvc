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
	<form:form action="updateproduct" method="post"
		modelAttribute="product">
		<table>
			<tr>
				<td><form:label path="id">ID : </form:label></td>
				<td><form:input path="id" value="" /></td>
			</tr>
			<tr>
				<td><form:label path="name">Name : </form:label></td>
				<td><form:input path="name" value="" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:textarea path="description" /></td>
			</tr>
			<tr>
				<td><form:label path="cost">Cost : </form:label>
				<td><form:input path="cost" />
			</tr>
			<tr>
				<td><form:label path="type">Type</form:label></td>
				<td><form:select path="type">
						<form:option value="Individual">Individual</form:option>
						<form:option value="Combo">Combo</form:option>
						<form:option value="Meal">Meal</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="food_type">Food Type</form:label></td>
				<td><form:select path="food_type">
						<form:option value="Veg">Veg</form:option>
						<form:option value="NonVeg">NonVeg</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="imgLink">Image : </form:label></td>
				<td><form:input path="imgLink" /></td>
			</tr>
			<tr>
				<td><form:label path="offer">Offers : </form:label></td>
				<td><form:input path="offer" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update Product">
			</tr>
		</table>
	</form:form>
</body>
</html>