<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>New Users page</title>
</head>
<body>
<h1>New Users page</h1>
<form:form method="POST" commandName="users" action="${pageContext.request.contextPath}/users/create.html" >
<table>
<tbody>
<tr>
<td>User name:</td>
<td><form:input path="name" /></td>
<td><form:errors path="name" cssStyle="color: red;"/></td>
</tr>

<tr>
<td>User age:</td>
<td><form:input path="age" /></td>
<td><form:errors path="age" cssStyle="color: red;"/></td>
</tr>

<tr>
<td>Is user admin:</td>
<td><form:checkbox path="admin" /></td>
</tr>

<tr>
<td>User's date of creation:</td>
<td><form:input path="createdDate" /></td>
<td><form:errors path="createdDate" cssStyle="color: red;"/></td>
</tr>

<tr>
<td><input type="submit" value="Create" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>