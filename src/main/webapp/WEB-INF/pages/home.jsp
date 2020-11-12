<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <jsp:include page="../static/css/cssBootstrap.jsp"/>
</head>
<body>

<jsp:include page="../static/included/header.jsp"/>

<div class="container-fluid mt-3">
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
            <c:forEach var="album" items="${albums}">
                <div class="btn-group p-2 col-sm-2 align-items-stretch">
                    <button class="btn btn-success btn-wrapper-checkbox" style="flex: 0 0 auto">
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
        <div class="row p-2">
            <h4>Ảnh</h4>
        </div>
        <div class="row">
            <c:forEach var="image" items="${images}">
                <div class="p-2 col-sm-2">
                    <div class="card">
                        <div class="card-img-top" style="height: 8em; overflow: hidden">
                            <img src="<c:url value="/${image.pathThumbnail}"/>"
                                 class="position-relative"
                                 alt="${image.name}"
                                 style="width: 100%">
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
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <div class="row flex-column">
            <h3 class="text-center">Chào mừng bạn đến với kho lưu trữ ảnh số 1 VN</h3>
            <h4 class="text-center">Vui lòng đăng nhập để sử dụng</h4>
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
</body>
</html>
