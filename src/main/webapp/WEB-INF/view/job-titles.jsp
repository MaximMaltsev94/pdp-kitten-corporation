<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ui:html title="Kitten Corporation">

    <div class="ui grid">
        <div class="row">
            <div class="four wide column">

                <ui:mainMenu activeItem="jobTitles"/>

                <div class="ui vertical buttons">

                    <div class="ui button" onclick="$('#createJobTitleModal').modal({onApprove : () => false}).modal('show')">
                        Создать должность
                    </div>
                </div>
            </div>
            <div class="twelve wide column">
                <div class="ui cards">
                    <c:forEach items="${jobTitles}" var="jobTitle">
                        <div class="card">
                            <div class="content">
                                <div class="header">${jobTitle.name}</div>
                            </div>
                            <button type="submit" form="delete-jobtitle-form-${jobTitle.id}" class="ui right labeled attached icon button">
                                <i class="ui trash icon"></i>
                                Удалить отдел
                            </button>
                        </div>
                        <form method="post" hidden id="delete-jobtitle-form-${jobTitle.id}" action="<c:url value="/job-titles/delete/${jobTitle.id}"/> "></form>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <ui:formSubmitModal title="Создать должность" modalId="createJobTitleModal" formId="createJobTitleForm">
        <form id="createJobTitleForm" class="ui form" method="post" action="<c:url value="/job-titles/create"/>">
            <div class="field">
                <label>Название должности</label>
                <input type="text" name="name" placeholder="Название отдела" required>
            </div>
        </form>
    </ui:formSubmitModal>
</ui:html>

