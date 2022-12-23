<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 05 / 01 / 2022 , Jan
  Time: 04:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>






<html>
<head>
    <title>Mke booking</title>
</head>
<body>
<h1><c:out value="${customer_email}" /></h1>
<h1><c:out value="${customer_name}" /></h1>
<form action="NewBooking" method="post">

    check_in   :  - <input type="date" id="check_in" name="check_in"> <br>
    check_out   :  -  <input type="date" id="check_out" name="check_out"> <br>
    date   :  -
    type   :  -  <select name="type" id="type">
    <option value="superior">superior</option>
    <option value="deluxe">deluxe</option>
    <option value="premium">premium</option>

</select> <br>
    adults   :  -  <select name="adults" id="adults">
    <option value="single">single</option>
    <option value="double">double</option>
    <option value="triple">triple</option>

</select> <br>
    status   :  -
    email   :  - <c:out value='${customer_email}' /><input type="hidden" name="email" value="<c:out value='${customer_email}' />" /><br>
    review   :  -
    <h2><c:out value="${error_message}" /></h2><br><br>
    <h2><c:out value="${count}" /></h2><br><br>

    <input type="submit" value="Save" />
    premium single
    premium double
    premium triple

</form>
</body>
</html>
