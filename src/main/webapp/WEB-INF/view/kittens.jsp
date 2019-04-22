<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ui:html title="Kitten Corporation">

    <div class="ui grid">
        <div class="row">
            <div class="four wide column">

                <ui:mainMenu activeItem="kittens"/>

                <div class="ui vertical buttons">
                    <div class="ui button" onclick="$('#hireKittenModal').modal({onApprove : () => false}).modal('show')">
                        Нанять котика
                    </div>
                </div>
            </div>
            <div class="twelve wide column">
                <ui:kittenList>
                    <c:forEach items="${kittens}" var="kitten">
                        <ui:kitten kitten="${kitten}"
                                   jobTitle="${kitten.jobTitle}"
                                   department="${kitten.department}"
                                   departments="${departments}"
                        />
                    </c:forEach>
                </ui:kittenList>
            </div>
        </div>
    </div>

    <ui:hireKittenModal/>
</ui:html>

