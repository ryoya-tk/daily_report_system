<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">

<form action="<c:url value='/login'/>" method="post">
<span>ID</span><input type="text" name="code" value="${emp.code }"><br>

<span>パスワード</span><input type="password" name="password"><br>
<br>
<input type="submit" value="送信">
</form>


</c:param>
</c:import>