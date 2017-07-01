<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Shop List page</title>
</head>
<body>
        <div class="container" role="main">
            <h1>User List</h1>
            <table class="table">
            
                <tbody>
                <c:forEach var="shop" items="${shopList}">
                    <tr>
                        <td>${shop.id}</td>
                        <td>${shop.name}</td>
                        <td>${shop.emplNumber} </td>                        
                        <td>
                            <a href="${pageContext.request.contextPath}/edit/${user.id}">Edit</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/delete/${user.id}">Delete</a>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- /container -->
        <div class="pagination">
            <ul>
                <c:choose>
                    <c:when test="${currentIndex == 1}">
                        <li class="disabled"><a href="#">&lt;&lt;</a></li>
                        <li class="disabled"><a href="#">&lt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/list/${1}">&lt;&lt;</a></li>
                        <li><a href="${pageContext.request.contextPath}/list/${currentIndex - 1}">&lt;</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                    <c:url var="pageUrl" value="/list/${i}"/>
                    <c:choose>
                        <c:when test="${i == currentIndex}">
                            <li class="active"><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${currentIndex == page.totalPages}">
                        <li class="disabled"><a href="#">&gt;</a></li>
                        <li class="disabled"><a href="#">&gt;&gt;</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/list/${currentIndex + 1}">&gt;</a></li>
                        <li><a href="${pageContext.request.contextPath}/list/${page.totalPages}">&gt;&gt;</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
</body>
</html>    
