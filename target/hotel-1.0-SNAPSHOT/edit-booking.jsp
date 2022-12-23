<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 05 / 01 / 2022 , Jan
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><c:out value="${admin_name}" /></h1>
<form action="EditBookingDone" method="post">
id   :  -<c:out value='${booking.id}' /><input type="hidden" name="id" value="<c:out value='${booking.id}' />" /><br>
check in   :  - <input type="text" name="check_in" size="45" value="<c:out value='${booking.check_in}' />"/><br>
check out  :  - <input type="text" name="check_out" size="45" value="<c:out value='${booking.check_out}' />"/><br>
date  :  -<c:out value='${booking.date}' /><input type="hidden" name="date" value="<c:out value='${booking.date}' />" /><br>
type   :  -  <select name="type" id="type">
    <option value="superior" <c:if test="${booking.type == 'superior' }">
        <c:out value = "${'selected'}" />
    </c:if>
    >superior</option>
    <option value="deluxe" <c:if test="${booking.type == 'deluxe' }">
        <c:out value = "${'selected'}" />
    </c:if>
    >deluxe</option>
    <option value="premium" <c:if test="${booking.type == 'premium' }">
        <c:out value = "${'selected'}" />
    </c:if>
    >premium</option>

</select> <br>

adults   :  -  <select name="adults" id="adults">
    <option value="single" <c:if test="${booking.type == 'single' }">
        <c:out value = "${'selected'}" />
    </c:if>
    >1</option>
    <option value="double" <c:if test="${booking.type == 'double' }">
        <c:out value = "${'selected'}" />
    </c:if>
    >2</option>
    <option value="triple" <c:if test="${booking.type == 'triple' }">
        <c:out value = "${'selected'}" />
    </c:if>
    >3</option>

</select>


status  :  - <input type="text" name="status" size="45" value="<c:out value='${booking.status}' />"/><br>
email  :  -<c:out value='${booking.email}' /> <input type="hidden" name="email" value="<c:out value='${booking.email}' />" /><br>
review  :  -<c:out value='${booking.review}' /><input type="hidden" name="review" value="<c:out value='${booking.review}' />" /><br>
    <br><br>
    <h2><c:out value="${error_message}" /></h2><br><br>
    <h2><c:out value="${count}" /></h2><br><br>

    <input type="submit" value="Save" />
</form>

</body>
</html>
