<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<table>
    <tr>
        <th>件名</th>
        <td><input type="text" name="title" value="${report.title }"></td>
    </tr>
    <tr>
        <th>日付</th>
        <td><input type="date" name="date" value="${report.date }"></td>
    </tr>
    <tr>
        <th>内容</th>
        <td><textarea rows="10" cols="30" name="content" value="${report.content }"></textarea></td>
    </tr>
</table>

<p><a href="<c:url value='/report/update?id=${report.id }'/>">更新する</a></p>
<p><a href="<c:url value='/report/destroy?id=${report.id }'/>">削除する</a></p>


</c:param>
</c:import>