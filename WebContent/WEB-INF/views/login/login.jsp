<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">

<form action="<c:url value='/login'/>" method="post">
ID<input type="text" name="id" value="${emp.id }"><br>

パスワード<input type="password" name="password"><br>

<input type="submit" value="送信">
</form>


</c:param>
</c:import>