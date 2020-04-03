<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>日報管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" href="<c:url value='/css/alert.css'/>">

</head>
<body>
<div id="wrapper">

<div id="header">
<h1>日報管理システム</h1>
</div>
<div id="login">
<h3>ようこそ。${login_emp.name}さん。</h3>
</div>
<div id="content">
${param.content }
</div>

<div id="footer">
by ryoya.tanaka
</div>

</div>



</body>
</html>