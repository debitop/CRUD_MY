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

<form method="post" action="/BookController">
   book id: <input type="text" value="${book.bookId}" readonly name="bookId"><br>
    stud id: <input type="text" value="${studId}" name="studId" readonly><br>
    author: <input type="text" value="${book.author}" name="author"><br>
    name: <input type="text" value="${book.name}" name="name"><br>
    <p><input type="submit" value="submit"></p><br>
</form>

</body>
</html>
