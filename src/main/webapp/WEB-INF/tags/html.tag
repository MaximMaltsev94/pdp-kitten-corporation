<%@ tag trimDirectiveWhitespaces="true"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>

<%@ attribute name="title" required="true"%>


<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<c:url value="static/semantic.min.css" />">

    <script src="<c:url value="static/jquery.min.js"/>"></script>
    <script src="<c:url value="static/semantic.min.js"/>"></script>
</head>

<body>

<div class="ui container">
    <jsp:doBody/>
</div>

</body>
</html>