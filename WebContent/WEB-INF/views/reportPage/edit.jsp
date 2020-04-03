<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<form action="<c:url value='/report/update'/>" method="post">
<table>
    <tr>
        <th>件名</th>
        <td><input type="text" name="title" value="${report.title }" required></td>
    </tr>
    <tr>
        <th>作成者</th>
        <td><c:out value="${login_emp.name }"/></td>
    <tr>
        <th>日付</th>
        <td><input type="date" name="date" value="${r_date}" required></td>
    </tr>
    <tr>
        <th>内容</th>
        <td><textarea  name="content" value="${report.content }" required></textarea></td>
    </tr>
</table>
<input type="submit" value="更新する">
</form>
<br>
<c:if test="${login_emp.code==report.employee.code }">
<p><a href="<c:url value='/report/destroy'/>">削除する</a></p>
</c:if>

</c:param>
</c:import>