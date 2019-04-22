<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ui:html title="Kitten Corporation">

    <div class="ui grid">
        <div class="row">
            <div class="four wide column">

                <ui:mainMenu activeItem="departments"/>

                <div class="ui vertical buttons">

                    <div class="ui button" onclick="$('#hireKittenModal').modal({onApprove : () => false}).modal('show')">
                        Нанять котика
                    </div>

                    <div class="ui button" onclick="$('#createDepartmentModal').modal({onApprove : () => false}).modal('show')">
                        Создать отдел
                    </div>
                </div>
            </div>
            <div class="twelve wide column">
                <ui:departmentList>
                    <c:forEach items="${departments}" var="department">
                            <ui:department
                                    departmentId="${department.id}"
                                    departmentName="${department.name}"
                                    kittenCount="${department.kittenCount}"
                                    maxKittenCount="${department.maxKittenCount}"/>
                    </c:forEach>
                </ui:departmentList>
            </div>
        </div>
    </div>

    <ui:formSubmitModal title="Создать отдел" modalId="createDepartmentModal" formId="createDepartmentForm">
        <form id="createDepartmentForm" class="ui form" method="post" action="<c:url value="/departments/create"/>">
            <div class="field">
                <label>Название отдела</label>
                <input type="text" name="name" placeholder="Название отдела" required>
            </div>
            <div class="field">
                <label>Максимальное количество котиков</label>
                <input type="number" name="maxKittenCount" placeholder="Максимальное количество котиков в отделе"
                       required>
            </div>
        </form>
    </ui:formSubmitModal>

    <ui:hireKittenModal/>
</ui:html>

