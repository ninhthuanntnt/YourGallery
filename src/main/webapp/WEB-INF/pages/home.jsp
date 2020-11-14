<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
    <link rel="stylesheet" href="<c:url value="/static/css/general.css"/>">
    <link rel="stylesheet" href="https://unpkg.com/vanilla-context@1.0.11/dist/vanilla-context.min.css">
</head>
<body>
<div id="contextmenu-container" style="position: fixed; display: none;"></div>
<jsp:include page="../static/included/header.jsp"/>

<div class="container-fluid p-5">
    <c:if test="${sessionScope.user != null}">
        <div class="row p-2">
            <button class="btn btn-success mr-2" id="btn-add-image-to-album">
                Thêm ảnh vào albums
            </button>
            <button class="btn btn-danger mr-2" id="btn-delete-items">
                Xóa mục đã chọn
            </button>
            <button class="btn btn-primary mr-2" id="btn-download-items">
                Tải xuống
            </button>
        </div>
        <div class="row p-2">
            <h4>Albums</h4>
        </div>
        <div class="row">
            <jsp:include page="../static/included/lstAlbums.jsp"/>
        </div>
        <div class="row p-2">
            <h4>Ảnh</h4>
        </div>
        <div class="row">
            <jsp:include page="../static/included/lstImages.jsp"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <div class="row flex-column">
            <h3 class="text-center">Chào mừng bạn đến với kho lưu trữ ảnh số 1 VN</h3>
            <h4 class="text-center">Vui lòng <a href="<c:url value="/dang-nhap"/>">đăng nhập</a> để sử dụng</h4>
        </div>
    </c:if>
</div>
<script src="<c:url value="/static/js/home.js"/>"></script>
<script>
    btnDeleteItems.onclick = ()=>{
        if(imgIds.length === 0 && albumIds.length === 0){
            alert("Vui lòng chọn ảnh hoặc album bạn muốn xóa");
        }else{
            let urlImgIds = "";
            let urlAlbumIds = "";
            for (let id of imgIds) {
                urlImgIds += "&imgIds=" + id;
            }
            for (let id of albumIds) {
                urlAlbumIds += "&albumIds=" + id;
            }

            if(urlAlbumIds.length === 0){
                urlImgIds = urlImgIds.substring(1, urlImgIds.length);
            }else{
                urlAlbumIds = urlAlbumIds.substring(1, urlAlbumIds.length)
            }

            window.location = "<c:url value="/xoa-muc-da-chon"/>?" + urlAlbumIds + urlImgIds;
        }
    }

    btnAddImgToAlbum.onclick = () => {
        if (imgIds.length === 0) {
            alert("Vui lòng chọn ảnh cần thêm");
        } else if (albumIds.length === 0) {
            alert("Vui lòng chọn album mà bạn muốn thêm ảnh vào");
        } else if (albumIds.length > 1) {
            alert("Vui lòng chọn 1 album để thêm ảnh vào");
        } else {
            let urlImgIds = ""
            for (let id of imgIds) {
                urlImgIds += "&imgIds=" + id;
            }
            window.location = "<c:url value="/them-anh-vao-album?"/>albumId=" + albumIds[0] + urlImgIds;
        }
    };
</script>
<jsp:include page="../static/js/downloadItem.jsp"/>
<jsp:include page="../static/js/jsBootstrap.jsp"/>
<scritpt src="<c:url value="/static/js/ctxmenu-native.1.0.0.js"/>"></scritpt>
</body>
</html>
