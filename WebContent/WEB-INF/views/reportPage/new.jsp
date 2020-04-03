<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<form action="<c:url value='/report/create'/>" method="post">
<table>
    <tr>
        <th>件名</th>
        <td><input type="text" name="title" required></td>
    </tr>
    <tr>
        <th>作成者</th>
        <td><c:out value="${login_emp.name }"/></td>
    <tr>
        <th>日付</th>
        <td><input type="date" name="date" value="${date_at }" required></input></td>
    </tr>
    <tr>
        <th>内容</th>
        <td><textarea name="content" required></textarea></td>
    </tr>
</table>
<input type="submit" value="更新"/>
</form>





</c:param>
</c:import>