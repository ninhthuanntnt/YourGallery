<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="user" value="${sessionScope.user}"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Thư viện ảnh</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/"/>">Trang chủ <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/user/album"/>">Albums</a>
            </li>
        </ul>
        <div>
            <c:if test="${user == null}">
                <a href="<c:url value="/dang-nhap"/>">
                    <button class="btn btn-primary">Đăng nhập</button>
                </a>
                <a href="<c:url value="/dang-ky"/>">
                    <button class="btn btn-success">Đăng ký</button>
                </a>
            </c:if>
            <c:if test="${user != null}">
                <label>Chào ${user.name}</label>
                <a href="<c:url value="/dang-xuat"/>">
                    <button class="btn btn-dark">Đăng xuất</button>
                </a>
            </c:if>
        </div>
    </div>
</nav>