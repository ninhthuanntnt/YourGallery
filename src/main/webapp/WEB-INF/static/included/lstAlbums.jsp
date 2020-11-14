<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${albums != null && albums.size() > 0}">
    <c:forEach var="album" items="${albums}">
        <div class="btn-group p-2 col-sm-2 align-items-stretch" data-id="${album.id}">
            <button class="btn btn-success" style="flex: 0 0 auto">
                <input type="checkbox"
                       name="album-checkbox"
                       value="${album.id}"
                       data-id="${album.id}"/>
            </button>
            <button class="btn btn-outline-success text-overflow-eclipse"
                    onclick="window.location = '<c:url value="/user/anh?albumId=${album.id}&albumName=${album.name}"/>'"
                    data-id="${album.id}">
                <span data-id="${album.id}">${album.name}</span>
            </button>
        </div>
    </c:forEach>
</c:if>
<c:if test="${albums == null || (albums != null && albums.size() == 0)}">
    <div class="p-2 col-sm-12">
        <h5><i>Không có album nào cả</i></h5>
    </div>
</c:if>
<script src="https://unpkg.com/vanilla-context@1.0.11/dist/vanilla-context.min.js"></script>
<script>
    const myAlbumNodes = [
        {
            renderer: 'Đổi tên',
            onClick: ({originEvent}) => {
                const cardBody = originEvent.target;
                window.location = "<c:url value="/user/doi-ten?albumId="/>" + cardBody.dataset.id;
            }
        },
        {
            renderer: 'Xóa',
            onClick: ({originEvent}) => {
                const target = originEvent.target;
                window.location = "<c:url value="/xoa-muc-da-chon?albumIds="/>" + target.dataset.id;
            }
        },
        {
            renderer: 'Tải xuống',
            onClick: ({originEvent}) => {
                const target = originEvent.target;
                window.location = "<c:url value="/tai-ve?albumIds="/>" + target.dataset.id;
            }
        }
    ];
    let btnAlbum = document.getElementsByClassName("btn-group");
    [].forEach.call(btnAlbum, (card) => {
        const basicTableContext = new VanillaContext(card, {
            nodes: myAlbumNodes
        });
    });
</script>