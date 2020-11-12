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
        <button type="button" class="btn btn-success mr-2" data-toggle="modal" data-target="#formModal">
            Thêm ảnh mới
            <c:if test="${album != null}">
                vào ${album.name}
            </c:if>
        </button>
        <button class="btn btn-danger mr-2" id="btn-delete-items">
            Xóa mục đã chọn
        </button>
        <c:if test="${album != null}">
            <button class="btn btn-danger mr-2" id="btn-remove-from-album">
                Xóa ảnh khỏi album
            </button>
        </c:if>
        <!-- Modal -->
        <div class="modal fade " id="formModal" data-backdrop="static" data-keyboard="false" tabindex="-1"
             aria-labelledby="formModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="formModalLabel">
                            Thêm ảnh mới
                            <c:if test="${album != null}">
                                vào ${album.name}
                            </c:if>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="<c:url value="/user/anh"/>" method="post" enctype="multipart/form-data">
                            <c:if test="${album != null}">
                                <input type="hidden" value="${album.id}" name="albumId"/>
                            </c:if>
                            <div class="form-group">
                                <label>Tên ảnh</label>
                                <input type="text" class="form-control" id="name" name="name"/>
                                <small class="form-text text-muted">Nếu không nhập tên thì tên của file sẽ được đặt theo
                                    tên file bạn tải lên</small>
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
    <c:if test="${album != null}">
        <div class="row p-2">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/user/album"/>">Album</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${album.name}</li>
                </ol>
            </nav>
        </div>
    </c:if>
    <div class="row">
        <c:forEach var="image" items="${images}">
            <div class="p-2 col-sm-2">
                <div class="card">
                    <div class="card-img-top" style="height: 8em; overflow: hidden">
                        <a href="<c:url value="/${image.pathThumbnail}"/>" target="_blank">
                            <img src="<c:url value="/${image.pathThumbnail}"/>"
                                 class="position-relative"
                                 alt="${image.name}"
                                 style="width: 100%">
                        </a>
                    </div>
                    <div class="card-body d-flex align-items-center">
                        <input type="checkbox"
                               name="img-checkbox"
                               value="${image.id}"
                               aria-label="Checkbox for following text input"
                               class="mr-3">
                        <p class="card-text">
                                ${(image.name.length() > 15)?image.name.substring(0,15):image.name}
                            <c:if test="${image.name.length() > 15}">
                                ...
                            </c:if>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="<c:url value="/static/js/home.js"/>"></script>
<jsp:include page="../static/js/deleteItem.jsp"/>
<script>
    let btnRemoveFromAlbum = document.getElementById("btn-remove-from-album");
    btnRemoveFromAlbum.onclick = ()=>{
        if(imgIds.length != 0){
            let urlImgIds = "";
            for (let id of imgIds) {
                urlImgIds += "&imgIds=" + id;
            }
            urlImgIds = urlImgIds.substring(0, urlImgIds.length);
            window.location = "<c:url value="/xoa-anh-khoi-album?"/>" + urlImgIds;
        }else{
            alert("Vui lòng chọn ảnh");
        }
    }
</script>
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
