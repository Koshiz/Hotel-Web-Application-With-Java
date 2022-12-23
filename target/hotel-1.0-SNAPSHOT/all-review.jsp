<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 04 / 01 / 2022 , Jan
  Time: 02:30 PM
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
            <th>email</th>
            <th>title</th>
            <th>comment</th>
            <th>score</th>
            <th>date</th>
            <th>Actions</th>





        </tr>
        <c:forEach var="review" items="${listReview}">
            <tr>
                <td><c:out value="${review.id}" /></td>
                <td><c:out value="${review.email}" /></td>
                <td><c:out value="${review.title}" /></td>
                <td><c:out value="${review.comment}" /></td>
                <td><c:out value="${review.score}" /></td>
                <td><c:out value="${review.date}" /></td>

                <td><a href="DeleteReview?id=<c:out value='${review.id}' />">Delete</a></td>


            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
