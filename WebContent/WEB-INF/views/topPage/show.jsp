<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
<img src="<c:out value="${pic.pass }"/>">
<br>

<table border="1">

    <tr>
        <th>社員番号</th>
        <th>名前</th>
        <th>メールアドレス</th>
        <th>携帯電話</th>

        <th>作成日</th>
        <th>更新日</th>
        <th>権限<th>
    </tr>
    <tr>
        <td><c:out value="${emp.code }"/></td>
        <td><c:out value="${emp.name }"/></td>
        <td><c:out value="${emp.mail }"/></td>
        <td><c:out value="${emp.phone }"/></td>

        <td><c:out value="${emp.created_at }"/></td>
        <td><c:out value="${emp.updated_at }"/></td>

        <c:choose>
        <c:when test="${emp.admin_flag }">
            <td><c:out value="あり"/></td>
        </c:when>
        <c:when test="${!(emp.admin_flag) }">
            <td><c:out value="なし"/></td>
        </c:when>
        </c:choose>
    </tr>


</table>
<br>
<br>





<c:if test="${admin==true }">
<a href="<c:url value='/toppage/edit?id=${emp.id }'/>"><c:out value="編集する" /></a>
</c:if>
<br>
<c:if test="${admin }">
<c:if test="${delete==0 }">
    <a href="<c:url value='/toppage/destroy?id=${emp.id }'/>"><c:out value="削除する" /></a>
</c:if>
</c:if>
<c:if test="${admin }">
<c:if test="${delete==1}">
    <a href="<c:url value='/toppage/restore?id=${emp.id }'/>"><c:out value="復活する" /></a>
</c:if>
</c:if>


</c:param>
</c:import>