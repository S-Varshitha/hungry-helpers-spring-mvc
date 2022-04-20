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
	<form:form modelAttribute="user" action="createuser">
		<table>
			<tr>
				<td><form:label path="name">Name : </form:label></td>
				<td><form:input path="name" />
				<td>
			</tr>
			<tr>
				<td><form:label path="email">Email : </form:label></td>
				<td><form:input path="email" />
				<td>
			</tr>
			<tr>
				<td><form:label path="password">Password : </form:label></td>
				<td><form:password path="password" />
				<td>
			</tr>
			<tr>
				<td><form:label path="phone">Contact Number : </form:label></td>
				<td><form:input path="phone" />
				<td>
			</tr>
			<tr>
				<td><form:label path="role">Role : </form:label></td>
				<td><form:input path="role" />
				<td>
			</tr>
			<tr rowspan="2">
				<td><input type="submit" value="Create User">
			</tr>
		</table>
	</form:form>


</body>
</html>