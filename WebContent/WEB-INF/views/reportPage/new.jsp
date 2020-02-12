<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<form action="<c:url value='/report/create'/>" method="post">
<p>件名</p><br>
<input type="text" name="title"><br>
<p>日付</p>
<input type="date" name="date" value="2020/01/01"><br>
<p>内容</p>
<textarea name="content"></textarea>
<p>タグ１</p>
<p>タグ２</p>

<input type="submit" value="更新">
</form>





</c:param>
</c:import>