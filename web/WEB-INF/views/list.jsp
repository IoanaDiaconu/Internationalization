<%--
  Created by IntelliJ IDEA.
  User: Ioana
  Date: 2/28/2016
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="user" type="tutorial.internationalization.model.User"--%>

<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>${nameLabel}</th>
        <th>${countryLabel}</th>
    </tr>
    </thead>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.country}</td>
        </tr>
    </c:forEach>
</table>
<spring:url value="/app/addUser" var="addUser"/>
<button onclick="location.href='${addUser}'">Add</button>
</body>
</html>
