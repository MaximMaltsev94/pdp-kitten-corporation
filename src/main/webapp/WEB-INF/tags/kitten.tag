<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<%@ attribute name="kitten" required="true" type="pdp.kitten.corporation.domain.Kitten" %>
<%@ attribute name="jobTitle" required="true" type="pdp.kitten.corporation.domain.JobTitle" %>
<%@ attribute name="department" required="true" type="pdp.kitten.corporation.domain.Department" %>
<%@ attribute name="departments" required="true" type="java.util.List" %>

<div class="card">
    <div class="content">
        <img class="right floated mini ui image"
             src="https://static.thenounproject.com/png/410088-200.png">
        <div class="header">
            ${kitten.name}
        </div>
        <div class="meta">
            ${jobTitle.name}
        </div>
        <div class="description">
            Возраст: ${kitten.age} <br/>
            Департамент:
            <c:choose>
                <c:when test="${department == null}">
                    Нет отдела
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/departments/${department.id}"/>">${department != null ? department.name : "Нет отдела"}</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="ui floating dropdown bottom attached button">
        <i class="add icon"></i>
        Действия с котиком
        <div class="menu">
            <div class="item"
                 onclick="$('#delete-kitten-department-modal-${kitten.id}').modal({onApprove : () => false}).modal('show')">
                Перевести в другой отдел
            </div>
            <div onclick="$('#departmentSelect-${kitten.id}').val('');

                    console.log('пдр');
                    $('#delete-kitten-department-form-${kitten.id}').submit()" class="item">Уволить из отдела
            </div>
            <div onclick="$('#delete-kitten-form-${kitten.id}').submit()" class="item">Уволить из компании</div>
        </div>
    </div>

    <ui:formSubmitModal title="Перевести котика в другой отдел" modalId="delete-kitten-department-modal-${kitten.id}"
                        formId="delete-kitten-department-form-${kitten.id}">
        <form id="delete-kitten-department-form-${kitten.id}" class="ui form" method="post"
              action="<c:url value="/kittens/save"/>">
            <div class="field">
                <label for="departmentSelect-${kitten.id}">Отдел</label>
                <select id="departmentSelect-${kitten.id}" name="department" class="ui fluid dropdown">
                    <option value="">Выбрать отдел</option>
                    <c:forEach items="${departments}" var="department">
                        <c:if test="${department.kittenCount < department.maxKittenCount}">
                            <option value="${department.id}">${department.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <input hidden type="text" name="id" value="${kitten.id}">
            <input hidden type="text" name="name" value="${kitten.name}">
            <input hidden type="text" name="age" value="${kitten.age}">
            <input hidden type="text" name="jobTitle" value="${kitten.jobTitle.id}">
        </form>
    </ui:formSubmitModal>

    <form id="delete-kitten-form-${kitten.id}" hidden method="post"
          action="<c:url value="/kittens/delete/${kitten.id}"/>"></form>
</div>


<script>
    $('.ui.dropdown').dropdown();
</script>