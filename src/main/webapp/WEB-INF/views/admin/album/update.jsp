<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <title>Update cd</title>
    
        <h1>Update cd</h1>
        
        <!-- BINDING MEDIANTE TAG FORM SPRING MVC -->
        <form:form method="POST" action="${pageContext.request.contextPath}/admin/album/saveupdate" modelAttribute="album">
            <table>
                <tr>
                    <td><form:hidden path="uid"/></td>
                </tr>   
                <form:label path="artist">Artist</form:label>
                <form:select path="artist.uid">
                    <form:options items="${artists}" itemLabel="name" itemValue="uid"/>
                </form:select>  
                
                <tr>
                    <td><form:label path="title">Album</form:label></td>
                    <td><form:input path="title"/></td>
                    <td><form:errors path="title" cssStyle="color: #ff0000"/></td>
                </tr>       
                <tr>
                    <td><form:label path="length">Length</form:label></td>
                    <td><form:input path="length"/></td>
                    <td><form:errors path="length" cssStyle="color: #ff0000"/></td>
                </tr> 
                <tr>
                    <td><form:label path="year">Year</form:label></td>
                    <td><form:input path="year"/></td>
                    <td><form:errors path="year" cssStyle="color: #ff0000"/></td>
                </tr>                 
                <tr>
                    <td><form:label path="genre">Genre</form:label></td>
                    <td><form:input path="genre"/></td>
                    <td><form:errors path="genre" cssStyle="color: #ff0000"/></td>
                </tr> 
                
                <tr>
                    <td><form:label path="label">Label</form:label></td>
                    <td><form:input path="label"/></td>
                    <td><form:errors path="label" cssStyle="color: #ff0000"/></td>
                </tr> 
                
                <tr>
                    <td><form:label path="producer">Producer</form:label></td>
                    <td><form:input path="producer"/></td>
                    <td><form:errors path="producer" cssStyle="color: #ff0000"/></td>
                </tr> 
                
                <tr>
                    <td><form:label path="image">Image</form:label></td>
                    <td><form:input path="image"/></td>
                    <td><form:errors path="image" cssStyle="color: #ff0000"/></td>
                </tr>
                
                <tr>
                    <td colspan="3">
                        <input type="submit" value="Confirm"/>
                    </td>
                </tr>               
            </table> 
                
        </form:form>
        
        <p>
            <a href="${pageContext.request.contextPath}/admin/album/list">Gestione cds</a>
        </p>
    
