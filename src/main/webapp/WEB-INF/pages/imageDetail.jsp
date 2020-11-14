<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/14/20
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Chi tiết</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
    <link rel="stylesheet" href="<c:url value="/static/css/general.css"/>">
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>
<div class="container-fluid mt-3">
    <div class="row justify-content-center">
        <div class="col-sm-3">
            <img src="<c:url value="/${image.path}"/>" alt="${image.name}" width="100%">
        </div>
        <div class="col-sm-3 flex-column">
            <ul class="list-group">
                <li class="list-group-item">Tên: ${image.name}</li>
                <li class="list-group-item">Dung lượng:
                    <fmt:formatNumber type = "number"
                                      pattern="###,###,###.###"
                                      value = "${size}" /> KB
                </li>
                <li class="list-group-item">Kích thước: ${width}x${height}</li>
                <li class="list-group-item">
                    Thời điểm tạo: <fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm"
                                                   value="${creationTime}" />
                </li>
                <li class="list-group-item">
                    Chỉnh sửa lần cuối: <fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm"
                                                        value="${lastModifiedTime}" />
                </li>
                <li class="list-group-item">
                    Truy cập lần cuối: <fmt:formatDate type="both" pattern="dd/MM/yyyy HH:mm"
                                                       value="${lastAccessTime}" />
                </li>
                <li class="list-group-item">
                    <a href="<c:url value="/xoa-muc-da-chon?imgIds=${image.id}"/>"
                        class="btn btn-danger">
                        Xóa
                    </a>
                    <a href="<c:url value="/tai-ve?imgIds=${image.id}"/>"
                       class="btn btn-primary">
                        Tải về
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
