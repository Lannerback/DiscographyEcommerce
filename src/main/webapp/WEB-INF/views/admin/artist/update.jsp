<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <title>Update artist</title>   
        <h1>Update cd</h1>
        
        <!-- BINDING MEDIANTE TAG FORM SPRING MVC -->
        <form:form method="POST" action="${pageContext.request.contextPath}/admin/artist/saveupdate" modelAttribute="artistBean">
            <table>
                
                <tr>
                    <td><form:hidden path="uid"/></td>
                </tr>
                <tr>
                    <td><form:label path="name">name</form:label></td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name" cssStyle="color: #ff0000"/></td>
                </tr> 
                
                <tr>
                    <td><form:label path="surname">Producer</form:label></td>
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
            <a href="${pageContext.request.contextPath}/admin/artist/list">Gestione cds</a>
        </p>

