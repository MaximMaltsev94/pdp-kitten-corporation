<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<%@ attribute name="activeItem" required="true" %>


<div class="ui vertical pointing menu">
    <a class="${activeItem eq 'departments' ? 'active' : ''} item" href="<c:url value="/departments"/>">
        Отделы
    </a>
    <a class="${activeItem eq 'kittens' ? 'active' : ''} item" href="<c:url value="/kittens"/>">
        Котики
    </a>
    <a class="${activeItem eq 'jobTitles' ? 'active' : ''} item" href="<c:url value="/job-titles"/>">
        Должности
    </a>
</div>