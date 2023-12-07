<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/signupAction.do">

            <h1 class="h3 mb-3 fw-normal">회원가입</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디" required>
                <label for="user_id">ID</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="form-floating">
                <input type="password" name="check_password" class="form-control" id="check_password" placeholder="비밀번호 확인" required>
                <label for="check_password">비밀번호 확인</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name" placeholder="이름" required>
                <label for="user_name">이름</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="생일" required>
                <label for="user_birth">생일</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign Up</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>