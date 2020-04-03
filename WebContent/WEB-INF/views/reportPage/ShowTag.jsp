<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
<c:param name="content">

<table border="1">
    <tr>
        <th>タイトル名</th>
    </tr>

    <c:forEach var="tag" items="${tags }">
     <tr>
        <td><c:out value="${tag.title }"/></td>
    </tr>
    </c:forEach>

</table>

<p><a href="<c:url value='/report/index'/>">リポート一覧</a></p><br>



</c:param>
</c:import>