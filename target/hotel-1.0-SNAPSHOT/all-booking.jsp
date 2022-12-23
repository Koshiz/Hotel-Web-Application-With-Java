<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 04 / 01 / 2022 , Jan
  Time: 03:59 AM
  To change this template use File | Settings | File Templates.
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
        <h1><c:out value="${admin_name}" /></h1>
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>id</th>
            <th>check in</th>
            <th>check out</th>
            <th>date</th>
            <th>type</th>
            <th>adults</th>
            <th>status</th>
            <th>email</th>
            <th>review</th>
            <th>Actions</th>



        </tr>
        <c:forEach var="booking" items="${listBooking}">
            <tr>
                <td><c:out value="${booking.id}" /></td>
                <td><c:out value="${booking.check_in}" /></td>
                <td><c:out value="${booking.check_out}" /></td>
                <td><c:out value="${booking.date}" /></td>
                <td><c:out value="${booking.type}" /></td>
                <td><c:out value="${booking.adults}" /></td>
                <td><c:out value="${booking.status}" /></td>
                <td><c:out value="${booking.email}" /></td>
                <td><c:out value="${booking.review}" /></td>
                <td>

                    <c:if test="${booking.status == 'pending' }">
                    <a href="EditBooking?id=<c:out value='${booking.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="DeleteBooking?id=<c:out value='${booking.id}' />">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
