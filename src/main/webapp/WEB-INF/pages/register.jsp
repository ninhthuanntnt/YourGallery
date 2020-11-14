<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>


<div class="container p-5">
    <div class="row justify-content-center mb-5">
        <h3>Đăng ký tài khoản</h3>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <form action="${pageContext.request.contextPath}/dang-ky" method="post">
                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input type="username" class="form-control" id="username" name="username" value="${user.username}">
                </div>
                <div class="form-group">
                    <label>Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" value="${user.password}">
                </div>
                <div class="form-group">
                    <label>Nhập lại mật khẩu</label>
                    <input type="password" class="form-control" id="re-password" name="re-password">
                </div>
                <div class="form-group">
                    <label>Tên</label>
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                </div>
                <c:if test="${errMsg != null}" >
                    <div class="form-group">
                        <label class="text-danger">${errMsg}</label>
                    </div>
                </c:if>
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../static/js/jsBootstrap.jsp"/>
</body>
</html>
