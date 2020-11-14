<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/14/20
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đổi tên</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>
<div class="container mt-3">
    <div class="row justify-content-center">
        <h3>Đổi tên</h3>
    </div>
    <div class="row justify-content-center">
        <form action="<c:url value="/user/doi-ten"/>"
              method="post"
              class="form-inline">
            <div class="form-group mx-sm-3 mb-2">
                <c:if test="${albumId != null}">
                    <input type="hidden" name="albumId" value="${albumId}">
                </c:if>
                <c:if test="${imageId != null}">
                    <input type="hidden" name="imageId" value="${imageId}">
                    <input type="hidden"
                           name="ext"
                           value="${ext}"
                           class="form-control"/>
                </c:if>
                <label for="name" class="sr-only">Tên</label>
                <div class="input-group">
                    <input type="text"
                           name="name"
                           value="${previousName}"
                           class="form-control"
                           id="name"
                           placeholder="Nhập tên ở đây">
                    <c:if test="${imageId != null}">
                        <div class="input-group-append">
                            <span class="input-group-text">${ext}</span>
                        </div>
                    </c:if>
                </div>

            </div>
            <div class="form-group">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Đổi tên</button>
        </form>
    </div>
</div>
<jsp:include page="../static/js/jsBootstrap.jsp"/>
</body>
</html>
