<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="g-3">
  <h1>${user.userName}님의 장바구니</h1>
  <br/>
  <ul>
    <c:forEach var="product" items="${products}">

    </c:forEach>
  </ul>

  <div class="text-end">
      <form method="post" action="/buyAction.do">
        <button class="btn btn-outline-light me-2">모두 구매하기</button>
      </form>
  </div>
</div>