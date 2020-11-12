<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/12/20
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    let btnDownloadItem = document.getElementById("btn-download-items");
    btnDownloadItem.onclick = ()=>{
        if(imgIds.length === 0 && albumIds.length === 0){
            alert("Vui lòng chọn ảnh hoặc album bạn muốn tải về");
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

            window.location = '<c:url value="/tai-ve"/>?' + urlAlbumIds + urlImgIds;
        }
    }
</script>
