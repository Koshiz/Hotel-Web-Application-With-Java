<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 03 / 01 / 2022 , Jan
  Time: 07:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>You have logged successfully</h1>
    <h1><c:out value="${admin_username}" /></h1>
    <h1><c:out value="${admin_name}" /></h1>
    <h1><a href="test.jsp">Test new</a></h1>
    <h2><a href="AllBookings">Bookings-</a><c:out value="${bookingCount}" /></h2>
    <h2><a href="AllCustomers">Customers-</a><c:out value="${customerCount}" /></h2>
    <h2><a href="AllReviews">Reviews -</a><c:out value="${reviewsCount}" /></h2>
    <br><br><br>
    <h1><a href="Logout">Logout</a></h1>


</div>
</body>
</html>
