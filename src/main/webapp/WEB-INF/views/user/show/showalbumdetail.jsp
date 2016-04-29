<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<title>Detail cd</title>


<div class="container">
    <table class="table-album">
        
        <caption>Album</caption>
        <thead>
            <tr>   
                
            </tr>
        </thead>  
        <tbody>
        <tr>
            <td>Artista</td>
            <td> ${cd.artist.name} </td>                                    
        </tr>  
        <tr>
            <td>Album</td> 
            <td> ${cd.title} </td>                                    
        </tr> 
        <tr>
            <td>Length</td> 
            <td> ${cd.length} </td>                                    
        </tr> 
        <tr>
            <td>Year</td> 
            <td> ${cd.year} </td>                                    
        </tr> 
        <tr>
            <td>Genre</td> 
            <td> ${cd.genre} </td>                                    
        </tr> 
        <tr>
            <td>Label</td> 
            <td> ${cd.label} </td>                                    
        </tr> 
        <tr>
            <td>Producer</td> 
            <td> ${cd.producer} </td>                                    
        </tr> 
        </tbody>
    </table> 
</div>

<p>      
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="${pageContext.request.contextPath}/admin/home">Gestione</a>            
    </sec:authorize>
</p>
<p>
    <a href="${pageContext.request.contextPath}/index">home</a>
</p>    