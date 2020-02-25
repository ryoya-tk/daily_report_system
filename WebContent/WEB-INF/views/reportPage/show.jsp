<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<table>
    <tr>
        <th>件名</th>
        <td><c:out value="${report.title }"/></td>
    </tr>
    <tr>
        <th>日付</th>
        <td><c:out value="${report.date_at }"/></td>
    </tr>
    <tr>
        <th>内容</th>
        <td><c:out value="${report.content }"/></td>
    </tr>
    <tr>
        <th>作成日時</th>
        <td><c:out value="${report.created_at }"/></td>
    </tr>
    <tr>
        <th>更新日時</th>
        <td><c:out value="${report.updated_at }"/></td>
    </tr>

</table>

<p><a href="<c:url value='/report/index'/>">日報一覧へ</a><p>
<p><a href="<c:url value='/report/edit?id=${report.id }'/>">編集する</a></p>



</c:param>
</c:import>
