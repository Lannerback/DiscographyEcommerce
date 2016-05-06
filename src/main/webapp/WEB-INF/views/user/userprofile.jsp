

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>YourProfile</title>

<script src="<c:url value="/resources/js/userprofile.js" /> "/></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

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


<form:form method="POST" action="${pageContext.request.contextPath}/user/changed" commandName="userBean">
    <table>                              
        <tr>
            <td><form:label path="username">username</form:label></td>
            <td><form:input path="username"/></td>
            <td><form:errors path="username" cssStyle="color: #ff0000"/></td>
        </tr> 

        <tr>
            <td><form:label path="password">password</form:label></td>
            <td><form:password onkeyup="validate()" path="password" required="true"/></td>
            <td><form:errors path="password" cssStyle="color: #ff0000"/>
                <i style="display:none;" id="passwordwarning" class="material-icons">warning</i>
            </td>
       
                    
        <td><input type="button" onclick="showpassword()" value="show password"/></td>
    </tr> 

    <tr>
        <td colspan="3">
            <input type="submit" value="Confirm"/>
        </td>
    </tr>               
</table>                 
</form:form>


