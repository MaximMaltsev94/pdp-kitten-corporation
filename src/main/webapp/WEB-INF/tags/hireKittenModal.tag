<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<%@attribute name="defaultDepartmentId" required="false" %>

<ui:formSubmitModal title="Нанять котика" modalId="hireKittenModal" formId="hireKittenForm">
    <form id="hireKittenForm" class="ui form" method="post" action="<c:url value="/kittens/save"/>">
        <div class="field">
            <label>Кличка котика</label>
            <input type="text" name="name" placeholder="Кличка котика" required>
        </div>
        <div class="field">
            <label>Возраст котика</label>
            <input type="number" name="age" placeholder="Возраст котика"
                   required>
        </div>

        <div class="field">
            <label>Отдел</label>
            <select name="department" class="ui fluid dropdown">
                <option value="">Выбрать отдел</option>
                <c:forEach items="${departments}" var="department">
                    <c:if test="${department.kittenCount < department.maxKittenCount}">
                        <option value="${department.id}">${department.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <c:choose>
            <c:when test="${defaultDepartmentId == null}">
                <div class="field">
                    <label>Должность</label>
                    <select name="jobTitle" class="ui fluid dropdown">
                        <option value="">Выбрать должность</option>
                        <c:forEach items="${jobTitles}" var="jobTitle">
                            <option value="${jobTitle.id}">${jobTitle.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:when>
            <c:otherwise>
                <input hidden type="text" name="department" value="${defaultDepartmentId}"/>
            </c:otherwise>
        </c:choose>

    </form>
</ui:formSubmitModal>