<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>


<div class="container">
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
            </form>
        </div>
    </div>
</div>

<jsp:include page="../static/js/jsBootstrap.jsp"/>
</body>
</html>
