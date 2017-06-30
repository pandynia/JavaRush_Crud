<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Users search page</title>
</head>
<body>
<h1>User List page</h1>

<table>
<thead>
<tr>
<th width="150px">name</th>
<th width="25px">age</th>
<th width="50px">is admin</th>
<th width="150px">createdDate</th>
</tr>
</thead>
<tbody>
<c:forEach var="user" items="${usersFound}">
<tr>
	<td>${user.name}</td>
	<td>${user.age}</td>
	<td>${user.admin}</td>
	<td>${user.createdDate}</td>	
</tr>
</c:forEach>
</tbody>
</table>

<a href="${pageContext.request.contextPath}/index">Home page</a>
</body>
</html>