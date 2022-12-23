<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 05 / 01 / 2022 , Jan
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Customer have logged successfully</h1>
    <h1><c:out value="${customer_email}" /></h1>
    <h1><c:out value="${customer_name}" /></h1>
    <h1><a href="make-booking.jsp">make booking</a></h1>
    <h1><a href="CustomerAllBookings">see booking</a></h1>
    <h1><a href="CustomerAllReviews">see reviews</a></h1>
    <br><br><br>
    <h1><a href="Logout">Logout</a></h1>







</div>
</body>
</html>
