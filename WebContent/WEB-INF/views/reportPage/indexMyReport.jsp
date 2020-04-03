<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">
<h1>日報一覧</h1>
<h2><a href="<c:url value='/index.html'/>">従業員管理画面</a></h2>
<h3>自分の日報一覧</h3>
<h3><a href="<c:url value='/report/index'/>">すべての日報一覧</a></h3>
<table border="1">
    <tr>
        <th>件名</th>
        <th>作成者</th>
        <th>日付</th>
        <th>内容</th>
    </tr>

    <c:forEach var="report" items="${reports }">
     <tr>
        <td><a href="<c:url value='/report/show?id=${report.id }'/>">
        ${report.title }</a></td>
        <td>${report.employee.name }</td>
        <td>${report.date_at }</td>
        <td>${report.content }</td>
    </tr>
    </c:forEach>

</table>

<p><a href="<c:url value='/report/new'/>">新しくつくる</a></p><br>
<p><a href="<c:url value='/newTag'/>">タグを追加する</a></p><br>

</c:param>
</c:import>