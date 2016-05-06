<%-- 
    Document   : create
    Created on : 6-mag-2015, 10.45.58
    Author     : Luis
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <title>Create cd</title>
    
        <h1>Create cd</h1>
        
        <!-- BINDING MEDIANTE TAG FORM SPRING MVC -->
        <form:form method="POST" action="${pageContext.request.contextPath}/admin/artist/save" modelAttribute="artistBean">
            <table>                
                <tr>
                    <td><form:label path="name">name</form:label></td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name" cssStyle="color: #ff0000"/></td>
                </tr> 
                
                <tr>
                    <td><form:label path="surname">surname</form:label></td>
                    <td><form:input path="surname"/></td>
                    <td><form:errors path="surname" cssStyle="color: #ff0000"/></td>
                </tr> 
                <tr>
                    <td colspan="3">
                        <input type="submit" value="Confirm"/>
                    </td>
                </tr>               
            </table> 
                
        </form:form>
        
        <p>
            <a href="${pageContext.request.contextPath}/admin/home">Gestione</a>
        </p>
   
