<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<div>
<a href="<c:url value='/showTag'/>">タグ一覧</a>&nbsp;&nbsp;
<a href="<c:url value='/report/index'/>">リポート一覧</a>
</div><br>

<c:if test="${error!=null }">
<c:out value="${error }"/>
</c:if>
<form action="<c:url value='/newTag'/>" method="post">
チーム名や部署名をタグとして設定できます。<br><br>
<input type="text" name="title"><br>
<input type="submit" value="更新"><br>
</form>





</c:param>
</c:import>