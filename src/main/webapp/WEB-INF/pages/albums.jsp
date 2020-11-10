<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Album page</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>

<div class="container-fluid p-5">
    <div class="row justify-content-center mb-3">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#formModal">
            Tạo album mới
        </button>

        <!-- Modal -->
        <div class="modal fade " id="formModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="formModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="formModalLabel">Album mới</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="<c:url value="/user/album"/>" method="post">
                            <div class="form-group">
                                <label>Tên album</label>
                                <input type="text" class="form-control" id="name" name="name" required/>
                            </div>
                            <button type="hidden" class="btn btn-primary d-none" id="submit"></button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                        <label for="submit" class="btn btn-success">Tạo</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <c:forEach var="album" items="${albums}">
            <div class="p-2 col-sm-2">
                <button class="btn btn-outline-success" style="width: 100%">${album.name}</button>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../static/js/jsBootstrap.jsp"/>
</body>
</html>
