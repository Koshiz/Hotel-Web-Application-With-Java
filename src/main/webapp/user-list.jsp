<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 03 / 01 / 2022 , Jan
  Time: 07:59 PM
  To change this template use File | Settings | File Templates.
  <td><c:out value="${hotel_admin.username}" /></td>

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>


    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>



        </tr>
        <c:forEach var="hotel_admin" items="${listUser2}">
            <tr>
                <td><c:out value="${hotel_admin.name}" /></td>

                <td><c:out value="${hotel_admin.password}" /></td>

                <td><c:out value="${hotel_admin.username}" /></td>



            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
