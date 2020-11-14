<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>


<div class="container p-5">
    <div class="row justify-content-center mb-5">
        <h3>Đăng nhập vào thư viện</h3>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <form action="${pageContext.request.contextPath}/dang-nhap" method="post">
                <div class="form-group">
                    <label>Tên đăng nhập</label>
                    <input type="username" class="form-control" id="username" name="username">
                </div>
                <div class="form-group">
                    <label>Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
                <small>Nếu chưa có tài khoản hãy <a href="<c:url value="/dang-ky"/>">đăng ký</a></small>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../static/js/jsBootstrap.jsp"/>
</body>
</html>
