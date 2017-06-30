<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>User List page</title>
</head>
<body>

<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px">id</th>
<th width="150px">name</th>
<th width="25px">age</th>
<th width="50px">is admin</th>
<th width="150px">createdDate</th>
<th width="100px">Actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="users" items="${userListPg}">
<tr>
<td>${users.id}</td>
<td>${users.name}</td>
<td>${users.age}</td>
<td>${users.admin}</td>
<td>${users.createdDate}</td>
<td>
<a href="${pageContext.request.contextPath}/users/edit/${users.id}.html">Edit</a><br/>
<a href="${pageContext.request.contextPath}/users/delete/${users.id}.html">Delete</a><br/>
</td>
</tr>
</c:forEach>
</tbody>
</table>

<c:url var="firstUrl" value="/pages/1.html" />
<c:url var="lastUrl" value="/pages/${page.totalPages}.html" />
<c:url var="prevUrl" value="/pages/${currentIndex - 1}.html" />
<c:url var="nextUrl" value="/pages/${currentIndex + 1}.html" />

<div class="pagination">
    <ul>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/pages/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == deploymentLog.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>