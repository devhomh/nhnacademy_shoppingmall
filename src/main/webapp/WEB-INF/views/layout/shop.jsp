<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall</title>

</head>
<body>

    <div class="mainContainer">
        <header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <c:url var="mypage_link" value="/mypage/index.do">
                        <c:param name="loginID" value="${sessionScope.loginID}"/>
                    </c:url>

                    <c:url var="shoppingcart_link" value="/mypage/cart.do">
                        <c:param name="loginID" value="${sessionScope.loginID}"/>
                    </c:url>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/index.do" class="nav-link px-2 text-secondary">Home</a></li>
                        <li><a href="${mypage_link}" class="nav-link px-2 text-white">마이페이지</a></li>
                        <li><a href="${shoppingcart_link}" class="nav-link px-2 text-white">장바구니</a></li>
                    </ul>

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                        <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
                    </form>

                    <div class="text-end">
                        <c:choose>
                            <c:when test="${not empty sessionScope.loginID}">
                                <form method="post" action="/logout.do">
                                    <button class="btn btn-outline-light me-2">로그아웃</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-outline-light me-2" href="/login.do" >로그인</a>
                                <a class="btn btn-warning" href="/signup.do" >회원가입</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </header>

        <main>
            <div class="album py-5 bg-light">
                <div class="container">
                    <jsp:include page="${layout_content_holder}" />
                </div>
            </div>

        </main>

        <footer class="text-muted py-5">
            <div class="container">
                <p class="float-end mb-1">
                    <a href="#">Back to top</a>
                </p>
                <p class="mb-1">shoppingmall example is © nhnacademy.com</p>
            </div>
        </footer>

    </div>
</body>
</html>