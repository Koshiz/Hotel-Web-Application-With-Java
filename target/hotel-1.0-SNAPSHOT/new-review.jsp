<%--
  Created by IntelliJ IDEA.
  User: devmith
  Date: 06 / 01 / 2022 , Jan
  Time: 04:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="NewReview" method="post">
    <c:out value='${param.test}' />
    id   :  - <c:out value='${param.id}' />  <input type="hidden" name="id" value="<c:out value='${param.id}' />" /><br>
    email   :  - <c:out value='${customer_email}' /> <input type="hidden" name="email" value="<c:out value='${customer_email}' />" /><br>
    title   :  -  <input type="text" name="title" size="45"/> <br>
    comment   :  -  <input type="text" name="comment" size="45"/> <br>
    score   :  -  <input type="text" name="score" size="45"/> <br>
    date   :  -   <br>


    <input type="submit" value="Save" />

</form>
</body>
</html>
