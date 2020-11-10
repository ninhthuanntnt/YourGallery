<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ảnh</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>
<jsp:include page="../static/included/header.jsp"/>

<div class="container-fluid p-5">
    <div class="row justify-content-center mb-3">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#formModal">
            Thêm ảnh mới
        </button>

        <!-- Modal -->
        <div class="modal fade " id="formModal" data-backdrop="static" data-keyboard="false" tabindex="-1"
             aria-labelledby="formModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="formModalLabel">Thêm ảnh mới</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="<c:url value="/user/anh"/>" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>Tên ảnh</label>
                                <input type="text" class="form-control" id="name" name="name" required/>
                                <small class="form-text text-muted">Nếu không nhập tên thì tên của file sẽ được đặt theo tên file bạn tải lên</small>
                            </div>
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" name="image"
                                               class="custom-file-input"
                                               id="inputGroupFile01"
                                               required/>
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
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
        <c:forEach var="image" items="${images}">
            <div class="p-2 col-sm-2">
                <button class="btn btn-outline-success" style="width: 100%">${image.name}</button>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="../static/js/jsBootstrap.jsp"/>
<script>
    $('input[type="file"]').on('change', function (e) {
        let fileName = e.target.files[0].name;
        console.log(fileName);
        $(this).next('.custom-file-label').html(fileName);
    });
</script>
</body>
</html>
