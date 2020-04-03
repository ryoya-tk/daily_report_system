<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">

<c:param name="content">

<h3>従業員管理ページ</h3>

<div id="index">

<a href="<c:url value='/toppage/new'/>">新しい従業員</a>
&nbsp;&nbsp;
<a href="<c:url value='/report/index'/>">日報のページ</a>
&nbsp;&nbsp;
<a href="<c:url value='/logout'/>">ログアウトする</a>
</div>

<h4>このページでは、従業員一覧を見ることができます。<br>
・名前をクリックすることで、その人物の詳細な情報が閲覧できます。<br>
・新しい従業員をクリックすると、従業員を新規作成できます。<br>
・日報のページをクリックすると、各従業員が作成した日報を閲覧することができます。</h4>
<br>
<table border="1">

    <tr>
        <th>社員番号</th>
        <th>名前</th>
        <th>メールアドレス</th>
        <th>携帯電話</th>
    </tr>
    <c:forEach var="emp" items="${empList }">
    <tr>
    <c:choose>
        <c:when test="${emp.delete_flag == 0}">
            <td><c:out value="${emp.code }"/></td>
            <td><a href="<c:url value='/toppage/show?id=${emp.id }'/>"><c:out value="${emp.name }"/></a></td>
            <td><c:out value="${emp.mail }"/></td>
            <td><c:out value="${emp.phone }"/></td>
        </c:when>

        <c:when test="${emp.delete_flag == 1 }">
            <td><c:out value="${emp.code }"/></td>
            <td><a href="<c:url value='/toppage/show?id=${emp.id }'/>"><c:out value="${emp.name }"/></a></td>
            <td colspan="2">削除ずみ</td>
        </c:when>

    </c:choose>




    </tr>
    </c:forEach>

</table>
<br>
<br>


<br>


</c:param>

</c:import>