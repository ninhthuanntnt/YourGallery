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
        <button type="button" class="btn btn-success mr-2" data-toggle="modal" data-target="#formModal">
            Tạo album mới
        </button>
        <button class="btn btn-danger mr-2" id="btn-delete-items">
            Xóa album đã chọn
        </button>
        <!-- Modal -->
        <div class="modal fade " id="formModal" data-backdrop="static" data-keyboard="false" tabindex="-1"
             aria-labelledby="formModalLabel" aria-hidden="true">
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
            <div class="btn-group p-2 col-sm-2 align-items-stretch">
                <button class="btn btn-success" style="flex: 0 0 auto">
                    <input type="checkbox"
                           name="album-checkbox"
                           value="${album.id}"/>
                </button>
                <button class="btn btn-outline-success"
                        onclick="window.location = '<c:url value="/user/anh?albumId=${album.id}&albumName=${album.name}"/>'">
                        ${album.name}
                </button>
            </div>
        </c:forEach>
    </div>
</div>

<script src="<c:url value="/static/js/home.js"/>"></script>
<jsp:include page="../static/js/deleteItem.jsp"/>
<jsp:include page="../static/js/jsBootstrap.jsp"/>
</body>
</html>
