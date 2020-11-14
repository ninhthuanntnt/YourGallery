<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${images != null && images.size() > 0}">
    <c:forEach var="image" items="${images}">
        <div class="p-2 col-sm-4 col-md-2">
            <div class="card">
                <div class="card-img-top" style="height: 8em; overflow: hidden">
                    <a href="<c:url value="/user/chi-tiet?imgId=${image.id}"/>">
                        <img src="<c:url value="/${image.pathThumbnail}"/>"
                             onerror="this.src = '<c:url value="/${image.path}"/>'"
                             class="position-relative"
                             alt="${image.name}"
                             style="width: 100%">
                    </a>
                </div>
                <div class="card-body d-flex align-items-center" data-id="${image.id}">
                    <input type="checkbox"
                           name="img-checkbox"
                           value="${image.id}"
                           aria-label="Checkbox for following text input"
                           class="mr-3"
                           data-id="${image.id}">
                    <span class="card-text text-overflow-eclipse" data-id="${image.id}">
                    <span data-id="${image.id}">${image.name}</span>
                </span>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>
<c:if test="${images == null || (images != null && images.size() == 0)}">
    <div class="p-2 col-sm-12">
        <h5><i>Không có ảnh nào cả</i></h5>
    </div>
</c:if>
<script src="https://unpkg.com/vanilla-context@1.0.11/dist/vanilla-context.min.js"></script>
<script>
    const myImageNodes = [
        {
            renderer: 'Tải xuống',
            onClick: ({originEvent}) => {
                const target = originEvent.target;
                window.location = "<c:url value="/tai-ve?albumIds="/>" + target.dataset.id;
            }
        },
        {
            renderer: 'Đổi tên',
            onClick: ({originEvent}) => {
                const target = originEvent.target;
                window.location = "<c:url value="/user/doi-ten?imageId="/>" + target.dataset.id;
            },
        },
        {
            renderer: 'Xóa',
            onClick: ({originEvent}) => {
                const target = originEvent.target;
                window.location = "<c:url value="/xoa-muc-da-chon?imgIds="/>" + target.dataset.id;
            }
        },
        {
            renderer: 'Chi tiết',
            onClick: ({originEvent}) => {
                const target = originEvent.target;
                window.location = "<c:url value="/user/chi-tiet?imgId="/>" + target.dataset.id;
            }
        }
    ];

    let cards = document.getElementsByClassName("card-body");
    [].forEach.call(cards, (card) => {
        const basicTableContext = new VanillaContext(card, {
            nodes: myImageNodes
        });
    });
</script>