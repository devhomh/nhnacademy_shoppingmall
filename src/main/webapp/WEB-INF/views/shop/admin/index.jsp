<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false"%>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${products}">
        <c:url var="view_link" value="/admin/product/view.do">
            <c:param name="productID" value="${product.productID}"/>
        </c:url>
        <c:url var="edit_link" value="/admin/product/edit.do">
            <c:param name="productID" value="${product.productID}"/>
        </c:url>
        <c:url var="delete_link" value="/admin/product/deleteAction.do">
            <c:param name="productID" value="${product.productID}"/>
        </c:url>
        <c:choose>
            <c:when test="${product.productImage eq 'no-image'}">
                <c:set var="img_link" value="${pageContext.request.contextPath}/resources/no-image.png"/>
            </c:when>
            <c:otherwise>
                <c:set var="img_link" value="${product.productImage}"/>
            </c:otherwise>
        </c:choose>
        <div class="col">
            <div class="card shadow-sm">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    <image xlink:href="${img_link}" width="100%" height="225"></image>
                </svg>

                <div class="card-body">
                    <p class="card-text">${product.modelName}</p>
                    <p class="card-text">${product.comment}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-outline-secondary" onclick=location.href="${view_link}">View</button>
                            <button type="button" class="btn btn-sm btn-outline-secondary" onclick=location.href="${edit_link}">Edit</button>
                            <form method="post" action="${delete_link}">
                                <button class="btn btn-sm btn-outline-secondary" >Delete</button>
                            </form>
                        </div>
                        <small class="text-bold">₩${product.unitCost}</small>
                        <small class="text-muted">남은 수량 : ${product.quantity}</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<span style="margin-top: 50px; height: 100%; display:flex; align-items: center; justify-content: center">
    <c:forEach begin="1" end="${totalPage}" var="pageNumber">
        <c:url var="pageURL" value="/admin/index.do">
            <c:param name="page" value="${pageNumber}" />
        </c:url>
        <a href="${pageURL}" style="margin-right: 5px">${pageNumber}</a>
    </c:forEach>
</span>
