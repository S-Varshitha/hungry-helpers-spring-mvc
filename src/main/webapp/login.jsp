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
	<form:form action="login" method="post"  modelAttribute="user">
		<table>
			<tr>
				<td><form:label path="email">Email : </form:label></td>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password : </form:label></td>
				<td><form:password path="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>