<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 04 / 01 / 2022 , Jan
  Time: 04:41 AM
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
            <th>email</th>
            <th>password</th>
            <th>first_name</th>
            <th>last_name</th>
            <th>phone1</th>
            <th>phone2</th>
            <th>address</th>
            <th>Actions</th>



        </tr>
        <c:forEach var="customer" items="${listCustomer}">
            <tr>
                <td><c:out value="${customer.email}" /></td>
                <td><c:out value="${customer.password}" /></td>
                <td><c:out value="${customer.first_name}" /></td>
                <td><c:out value="${customer.last_name}" /></td>
                <td><c:out value="${customer.phone1}" /></td>
                <td><c:out value="${customer.phone2}" /></td>
                <td><c:out value="${customer.address}" /></td>
                <td><a href="DeleteCustomer?email=<c:out value='${customer.email}' />">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
