
<%-- 
    Document   : create
    Created on : 6-mag-2015, 10.45.58
    Author     : Luis
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <title>Create cd</title>
   
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
            
        <h1>Create cd</h1>

        <!-- BINDING MEDIANTE TAG FORM SPRING MVC -->
        <form:form method="POST" action="${pageContext.request.contextPath}/admin/album/save" enctype="multipart/form-data" commandName="album">
            <table>
                <form:label path="artist">Artist</form:label>
                <form:select path="artist.uid">
                    <form:options items="${artists}" itemLabel="name" itemValue="uid"/>
                </form:select>
                <tr>
                    <td><form:label path="title">Title</form:label></td>
                    <td><form:input path="title"/></td>
                    <td><form:errors path="title" cssStyle="color: #ff0000"/></td>
                </tr>       
                <tr>
                    <td><form:label path="length">Length</form:label></td>
                    <td><form:input path="length"/></td>
                    <td><form:errors path="length" cssStyle="color: #ff0000"/></td>
                </tr> 
                <tr>
                    <!--td><fmt:formatDate value="${album.year}" var="dateString" pattern="dd/MM/yyyy" /></td-->
                    <!--td><form:input path="year" value="${dateString}"/></td-->
                    <td><form:label path="year" >Year</form:label></td>
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
                    <td>File to upload:</td><td><input type="file" name="file" /></td>
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

