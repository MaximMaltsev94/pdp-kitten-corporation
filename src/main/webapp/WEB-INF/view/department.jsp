<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ui:html title="Kitten Corporation">

    <div class="ui grid">
    <div class="row">
        <div class="four wide column">

            <ui:mainMenu activeItem="departments"/>

            <div class="ui vertical buttons">
                <button class="ui button" ${department.kittenCount >= department.maxKittenCount ? 'disabled' : ''}
                        onclick="$('#hireKittenModal').modal({onApprove : () => false}).modal('show')"
                >Нанять котика в отдел </button>
            </div>
        </div>


        <div class="twelve wide column">
            <ui:departmentList>
                <ui:department
                        departmentId="${department.id}"
                        departmentName="${department.name}"
                        kittenCount="${department.kittenCount}"
                        maxKittenCount="${department.maxKittenCount}"/>
            </ui:departmentList>

            <ui:kittenList>
                <c:forEach items="${department.kittens}" var="kitten">
                    <ui:kitten kitten="${kitten}" jobTitle="${kitten.jobTitle}" department="${department}" departments="${departments}"/>
                </c:forEach>
            </ui:kittenList>

    </div>
    <ui:formSubmitModal title="Нанять котика в отдел" modalId="hireKittenModal" formId="hireKittenForm">
        <form id="hireKittenForm" method="post" action="<c:url value="/kittens/set-kitten-department"/>" class="ui form" >
            <div class="field">
                <label>Котик</label>
                <select required name="kittenId" class="ui fluid dropdown">
                    <option value="">Выбрать котика</option>
                    <c:forEach items="${kittens}" var="kitten">
                        <c:if test="${kitten.department.id != department.id}">
                            <option value="${kitten.id}">
                                    ${kitten.name}: ${kitten.jobTitle.name}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <input hidden type="text" name="departmentId" value="${department.id}"/>
        </form>

    </ui:formSubmitModal>
</ui:html>

