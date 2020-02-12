<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">

<form action="<c:url value='/toppage/update'/>" method="post">
社員番号<br>
<input type="text" name="code" value="${emp.code}">
<br>
名前<br>
<input type="text" name="name" value="${emp.name}">
<br>
メールアドレス<br>
<input type="text" name="mail" value="${emp.mail }">
<br>
携帯電話<br>
<input type="text" name="phone" value="${emp.phone }">
<br>
<input type="submit" value="送信">









</form>


</c:param>




</c:import>