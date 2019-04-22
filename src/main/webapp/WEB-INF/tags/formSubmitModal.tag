<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>


<%@ attribute name="title" required="true" %>
<%@ attribute name="modalId" required="true" %>
<%@ attribute name="formId" required="true" %>

<div id="${modalId}" class="ui modal">
    <div class="header">
        ${title}
    </div>
    <div class="content">
        <jsp:doBody/>
    </div>
    <div class="actions">
        <div class="ui black deny button">
            Отмена
        </div>
        <button type="submit" form="${formId}" class="ui positive right labeled icon button">
            Продолжить
            <i class="checkmark icon"></i>
        </button>
    </div>
</div>