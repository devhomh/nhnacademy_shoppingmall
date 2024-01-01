<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="g-3">
    <h1>${user.userName}님 환영합니다!</h1>

    <div>
        <h3>주문 내역</h3>
        <ul></ul>
    </div>

    <c:url var="user_edit" value="/signup.do">
        <c:param name="userID" value="${user.userId}"/>
    </c:url>
    <c:url var="user_delete" value="/user/delete.do">
        <c:param name="userID" value="${user.userId}"/>
    </c:url>
    <div class="btn-group" style="margin-top: 50px">
        <button type="button" class="btn btn-sm btn-outline-secondary" onclick=location.href="${user_edit}">회원 정보 수정</button>
        <form method="post" action="${user_delete}">
            <button class="btn btn-sm btn-outline-secondary" id="deleteBtn">회원 탈퇴</button>
        </form>
    </div>
</div>

<script>
    const btn_delete = document.getElementById("deleteBtn");

    const deleteCheck = (event) => {
        const confirm = window.confirm("정말로 탈퇴 하시겠습니까?");
        if(confirm){
            alert("탈퇴 완료 되었습니다.");
            window.location.replace("/index.do")
        } else{
            event.preventDefault();
        }
    }

    btn_delete.addEventListener("click", deleteCheck);
</script>