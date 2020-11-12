
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</script>