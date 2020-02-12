<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">

<ul type="square">
<li><c:out value="${emp.code }"/></li>
<li><c:out value="${emp.name }"/></li>
<li><c:out value="${emp.mail }"/></li>
<li><c:out value="${emp.phone }"/></li>
<li><c:out value="${emp.admin_flag }"/></li>
<li><c:out value="${emp.created_at }"/></li>
<li><c:out value="${emp.updated_at }"/></li>
<li><c:out value="${emp.delete_flag }"/></li>
</ul>
<br>
<br>

<c:if test="${emp.admin_flag }">
<a href="<c:url value='/toppage/edit?id=${emp.id }'/>"><c:out value="編集する" /></a>
</c:if>
<br>
<c:if test="${emp.admin_flag }">
<a href="<c:url value='/toppage/destroy?id=${emp.id }'/>"><c:out value="削除する" /></a>
</c:if>

</c:param>
</c:import>