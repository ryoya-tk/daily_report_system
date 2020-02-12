<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">
<h1>日報一覧</h1>
<table border="1">
    <tr>
        <th>件名</th>
        <th>日付</th>
        <th>内容</th>
    </tr>
    <tr>
    <c:forEach var="report" items="${reports }">
        <td><a href="/report/show?id=${report.id }">${report.title }</a></td>
        <td>${report.date_at }</td>
        <td>${report.content }</td>
    </c:forEach>
    </tr>
</table>

<p><a href="<c:url value='/report/new'/>">新しくつくる</a></p>

</c:param>
</c:import>