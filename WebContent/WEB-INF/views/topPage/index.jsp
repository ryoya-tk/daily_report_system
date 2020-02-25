<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">

<c:param name="content">

<h2>日報管理システムへようこそ</h2>
<h3><a href="<c:url value='/login'/>">ログイン</a></h3>
<h3><a href="<c:url value='/logout'/>">ログアウト</a></h3>
<br>
<table border="1">

    <tr>
        <th>名前</th>
        <th>メールアドレス</th>
        <th>携帯電話</th>
    </tr>
    <c:forEach var="emp" items="${empList }">
    <tr>
        <td><a href="<c:url value='/toppage/show?id=${emp.id }'/>"><c:out value="${emp.name }"/></a></td>
        <td><c:out value="${emp.mail }"/></td>
        <td><c:out value="${emp.phone }"/></td>

        <c:if test="${emp.admin_flag==true }">
        <td><a href="<c:url value='/employees/edit'/>">編集する</a></td>
        </c:if>

    </tr>
    </c:forEach>

</table>
<br>
<br>
<a href="<c:url value='/toppage/new'/>">新しい従業員</a>
<br>
<a href="<c:url value='/report/index'/>">日報のページ</a>


</c:param>

</c:import>