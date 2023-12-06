<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
  <div class="p-2">
    <form method="post" action="/admin/addProductAction.do">

      <h1 class="h3 mb-3 fw-normal">상품 추가</h1>

      <div class="form-floating">
        <select name = "categories" class="form-control">
          <c:forEach var="category" items="${categories}">
              <option value="${category.categoryID}">${category.categoryName}</option>
          </c:forEach>
        </select>
        <label for="product_modelnumber">카테고리</label>
      </div>

      <div class="form-floating">
        <input type="text" name="product_modelnumber" class="form-control" id="product_modelnumber" placeholder="모델 넘버" required>
        <label for="product_modelnumber">모델 넘버</label>
      </div>

      <div class="form-floating">
        <input type="text" name="product_modelname" class="form-control" id="product_modelname" placeholder="모델 이름" required>
        <label for="product_modelname">모델 이름</label>
      </div>

      <div class="form-floating">
        <input type="number" name="product_quantity" class="form-control" id="product_quantity" placeholder="수량" required>
        <label for="product_quantity">수량</label>
      </div>

      <div class="form-floating">
        <input type="number" name="product_price" class="form-control" id="product_price" placeholder="가격" required>
        <label for="product_price">가격</label>
      </div>

      <div class="form-floating">
        <input type="text" name="product_comment" class="form-control" id="product_comment" placeholder="상품 설명" required>
        <label for="product_comment">상품 설명</label>
      </div>

      <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">추가</button>

      <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

    </form>
  </div>
</div>