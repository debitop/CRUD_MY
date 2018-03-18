<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 08.03.2018
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="2">
    <tr>
        <td>id</td>
        <td>userId</td>
        <td>author</td>
        <td>title</td>
        <td colspan="2">actions</td>
    </tr>

    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.bookId}"></c:out></td>
            <td><c:out value="${book.studentId}"></c:out></td>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.name}"></c:out></td>
            <td><a href="/BookController?action=delete&bookId=${book.bookId}&studId=${book.studentId}">delete</a></td>
            <td><a href="/BookController?action=edit&bookId=${book.bookId}&studId=${book.studentId}">edit</a></td>
        </tr>
    </c:forEach>

</table>
<a href="/StudentController?action=list">Students</a>
<p><a href="/BookController?action=create&studId=${studentId}">Create</a></p>
</body>
</html>
