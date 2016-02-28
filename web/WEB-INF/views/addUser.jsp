<%--
  Created by IntelliJ IDEA.
  User: Ioana
  Date: 2/28/2016
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:url value="/app/add" var="userActionUrl"/>

<form:form modelAttribute="user" method="post"  action="${userActionUrl}">
    <form:hidden path="id"/>
    <spring:bind path="name">
        <label>${nameLabel}</label>
        <div>
            <form:input path="name" id="name"/>
        </div>
    </spring:bind>

    <spring:bind path="country">
        <label>${countryLabel}</label>
        <div>
            <form:input path="country" id="country"/>
        </div>
    </spring:bind>

    <button type="submit">ADD</button>
</form:form>
</body>
</html>
