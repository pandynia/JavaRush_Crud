<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Users search page</title>
</head>
<body>
<h1>User List page</h1>
<form:form method="GET" name="searchString" action="${pageContext.request.contextPath}/users/search-result.html">

<table>
<tbody>
<tr>
<td>User name:</td>
<td><input type="text" id="txt" name="searchString" size="100" /></td>
<td><form:errors path="searchField" cssStyle="color: red;"/></td>
</tr>

<tr>
<td><button id="button-id" input type="submit">Search</button></td>
</tr>
</tbody>
</table>
</form:form>

</body>
</html> 


<a href="${pageContext.request.contextPath}/index">Home page</a>
</body>
</html>