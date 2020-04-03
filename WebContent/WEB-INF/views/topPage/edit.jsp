<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
<c:if test="${errors != null}">

        入力内容にエラーがあります。<br />
    <div id="flush_error">
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach><br>
    </div>


</c:if>

<form action="<c:url value='/toppage/update'/>" method="post">
＊社員番号(半角数字8桁です)<br>
<input type="text" name="code" value="${emp.code}" required>
<br>
<br>
＊名前(姓名を入力してください。スペースはいりません)<br>
<input type="text" name="name" value="${emp.name}" required>
<br>
<br>
＊メールアドレス<br>
<input type="email" name="mail" value="${emp.mail }" required>
<br>
<br>
携帯電話(ハイフンはいりません)<br>
<input type="tel" name="phone" value="${emp.phone }">
<br>
<br>
パスワード(半角英数字８桁以上です)<br>
変更する場合のみ入力してください。<br>
<input type="password" name="password" >
<br>
<br>
＊権限<br>
※権限とは従業員ページで、従業員の追加や削除をする機能のことです。<br>
<input type="radio" name="admin" value="true" >権限あり<br>
<input type="radio" name="admin" value="false" checked>権限なし<br>
<br>




＊は入力必須です。<br>
<input type="submit" value="送信">
</form>
<br>
<br>
以下のURLから画像をアップロードして、画像を設定することができます。<br>
⇒<a href="<c:url value='/upload/new'/>">画像をアップロードする</a>


</c:param>




</c:import>