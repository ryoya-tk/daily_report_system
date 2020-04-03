<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">

<c:param name="content">

<h2>日報管理システムへようこそ</h2>
<h3><a href="<c:url value='/index.html'/>">トップページへ</a></h3>
<br>

<form method="post" enctype="multipart/form-data" action="<c:url value='/upload/create'/>">

<input type="file" name="file"><br>

<input type="submit" value="アップロード">



</form>

</c:param>

</c:import>