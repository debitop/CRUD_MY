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
<form method="post" action="/StudentController">
    id: <input readonly name="studId" value="${student.studId}"><br>
    firsname: <input name="firstName" value="${student.firstName}"><br>
    lastname: <input name="lastName" value="${student.lastName}"><br>
    tel : <input name="tel" value="${student.tel}"><br>
    date : <input name="dob" value="${student.dob}"><br>
    <p><input type="submit" value="OK"></p>
</form>
</body>
</html>
