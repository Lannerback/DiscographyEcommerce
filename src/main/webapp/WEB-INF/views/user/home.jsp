
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<sec:authentication var="principal" property="principal" />

<c:if test="${response != null && response.success == false}">
    <div class="notification_failed">
        ${response.message}
    </div>
</c:if>

<c:if test="${response != null && response.success == true}">
    <div class="notification_success">
        ${response.message}
    </div>
</c:if>


<h1>Hello ${principal.username}</h1>        
<a href="${pageContext.request.contextPath}/user/userprofile">profile</a>
