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
<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Last Name</td>
        <td>tel</td>
        <td>dob</td>
        <td colspan="3">action</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td><c:out value="${student.studId}"></c:out></td>
            <td><c:out value="${student.firstName}"></c:out></td>
            <td><c:out value="${student.lastName}"></c:out></td>
            <td><c:out value="${student.tel}"></c:out></td>
            <td><c:out value="${student.dob}"></c:out></td>
            <td><a href="/StudentController?action=delete&studId=${student.studId}">delete</a></td>
            <td><a href="/StudentController?action=edit&studId=${student.studId}">edit</a></td>
            <td><a href="/BookController?action=list&studId=${student.studId}">Book list</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="/StudentController?action=create">Create Student</a></p>
</body>
</html>
