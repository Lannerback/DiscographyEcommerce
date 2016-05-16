<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <title>cds' List</title>
    
        <h1>List cds</h1>

        <c:if test="${albums == null || albums.isEmpty()}">
            Nessun cd ancora presente.
        </c:if>    
            
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

        <div>&nbsp;</div>    

        <table>
            <thead>
                <tr>
                    <th>Artista</th>
                    <th>Album</th> 
                    <th>Length</th> 
                    <th>Year</th> 
                    <th>Genre</th> 
                    <th>Label</th> 
                    <th>Producer</th>
                    <th> 
                        <form method="post" action="${pageContext.request.contextPath}/album/reset" >
                            <input type="submit" name="reset" value="reset" /> 
                        </form>
                    </th>

                </tr>
            </thead>    
            <c:forEach var="cd" items="${albums}" varStatus="loop">
                <tr>
                    <td>
                        ${cd.artist.name}
                    </td>
                    <td>
                        ${cd.title}
                    </td> 
                    <td>
                        ${cd.length}
                    </td> 
                    <td>
                        ${cd.year}
                    </td> 
                    <td>
                        ${cd.genre}
                    </td>
                    <td>
                        ${cd.label}
                    </td> 
                    <td>
                        ${cd.producer}
                    </td> 

                    <td>

                        <form action="${pageContext.request.contextPath}/admin/album/remove/${cd.uid}" method="post">                            
                            <!--input type="hidden" name="removecd" value="${cd.uid}"/-->
                            <input type="submit" name="removebutton" value="remove"/>
                        </form>
                    </td>

                    <td>                        
                        <form action="${pageContext.request.contextPath}/admin/album/update/${cd.uid}" method="post">                                                        
                            <input type="submit" value="update"  />
                        </form>
                    </td>

                    <td>                        
                        <form method="POST" action="/DiscographyEcommerce/showalbum/${cd.uid}">
                            <input type="submit" value="showdetail" />
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <p>
            <a href="${pageContext.request.contextPath}/admin/album/add">Inserisci cd</a>                       
            <a href="${pageContext.request.contextPath}/admin">Admin Management</a>
        </p>
        <br><br>    
        <h2>Welcome : ${pageContext.request.userPrincipal.name} </h2>

        <c:url value="/logout" var="logoutUrl" />
        <a href="${logoutUrl}">Log Out</a>   
