<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<%@ attribute name="departmentId" required="true" %>
<%@ attribute name="departmentName" required="true" %>
<%@ attribute name="kittenCount" required="true" %>
<%@ attribute name="maxKittenCount" required="true" %>

<div class="item">
    <div class="image">
        <img src="https://png.pngtree.com/svg/20170525/0ef5d9599c.svg">
    </div>
    <div class="content">
        <div class="meta">
            <span class="cinema">Департамент</span>
        </div>
        <a class="header" href="<c:url value="/departments/${departmentId}"/>">${departmentName}</a>
        <div class="description">
            <p>
                Количество котиков в отделе: ${kittenCount}
            </p>
        </div>
        <div class="extra">
            <form method="post" action="<c:url value="/departments/delete/${departmentId}"/>">
                <button type="submit" class="ui right floated right labeled red icon button">
                    Расформировать отдел
                    <i class="trash alternate icon"></i>
                </button>
            </form>
            <div class="ui label">Число рабочих мест: ${maxKittenCount}</div>
        </div>
    </div>
</div>