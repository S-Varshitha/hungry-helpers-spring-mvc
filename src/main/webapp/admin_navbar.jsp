<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: rgb(117, 116, 116);
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: rgb(0, 0, 0);
}
</style>
</head>
<body>
	<ul>
		<li><a href="readuser">Create User</a></li>
		<li><a href="viewusers">View User</a></li>
		<li><a href="readproduct">Create Product</a></li>
		<li><a href="viewproduct">View Product</a></li>
		<li><a href="readoffer">Create Offer</a></li>
		<li><a href="viewoffers">View Offers</a><li>
		<li><a href="history">View History</a></li>
		<li style="float: right"><a class="active" href="logout">Logout</a></li>
	</ul>
</body>
</html>