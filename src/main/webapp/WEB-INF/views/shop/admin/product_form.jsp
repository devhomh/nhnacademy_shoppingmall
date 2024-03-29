<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
  <div class="p-2">
    <c:url var="add_link" value="/admin/product/addAction.do"/>
    <c:choose>
      <c:when test="${empty product}">
        <c:set var="action" value="${add_link}" />
      </c:when>
      <c:otherwise>
        <c:url var="edit_link" value="/admin/product/editAction.do">
          <c:param name="productID" value="${product.productID}"/>
        </c:url>
        <c:set var="action" value="${edit_link}"/>
      </c:otherwise>
    </c:choose>

    <form method="post" action="${action}">

      <h1 class="h3 mb-3 fw-normal">상품 추가</h1>

      <div class="form-floating">
        <select name = "categories" class="form-control">
          <c:forEach var="category" items="${categories}">
            <option value="${category.categoryID}" <c:if test="${product.categoryID == category.categoryID}">selected</c:if>>
                ${category.categoryName}
            </option>
          </c:forEach>
        </select>
        <label for="product_modelnumber">카테고리</label>
      </div>

      <div class="form-floating">
        <input type="text" name="product_modelnumber" class="form-control" id="product_modelnumber" placeholder="모델 넘버" value="${product.modelNumber}" required>
        <label for="product_modelnumber">모델 넘버</label>
      </div>

      <div class="form-floating">
        <input type="text" name="product_modelname" class="form-control" id="product_modelname" placeholder="모델 이름" value="${product.modelName}" required>
        <label for="product_modelname">모델 이름</label>
      </div>

      <div class="form-floating">
        <input type="number" name="product_quantity" class="form-control" id="product_quantity" placeholder="수량" value="${product.quantity}" required>
        <label for="product_quantity">수량</label>
      </div>

      <div class="form-floating">
        <input type="number" name="product_price" class="form-control" id="product_price" placeholder="가격" value="${product.unitCost}" required>
        <label for="product_price">가격</label>
      </div>

      <div class="form-floating">
        <input type="text" name="product_comment" class="form-control" id="product_comment" placeholder="상품 설명" value="${product.comment}" required>
        <label for="product_comment">상품 설명</label>
      </div>

      <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">${empty product ? "추가" : "변경"}</button>

      <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

    </form>
  </div>
</div>