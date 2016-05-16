<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <title>cds' List</title>  

       <table>
            <h1>Artists</h1>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>                                         
                </tr>
            </thead>    
            <c:forEach var="artist" items="${artists}" varStatus="loop">
                <tr>
                    <td>
                        ${artist.name}
                    </td>
                    <td>
                        ${artist.surname}
                    </td>  
                     <td>
                        <form action="${pageContext.request.contextPath}/admin/artist/remove/${artist.uid}" method="post">                            
                            <!--input type="hidden" name="removeartist" value="${artist.uid}"/-->
                            <input type="submit" value="remove" />
                        </form>
                    </td>

                    <td>                        
                        <form action="${pageContext.request.contextPath}/admin/artist/update/${artist.uid}" method="post">                                                        
                            <input type="submit" value="update"  />
                        </form>
                    </td>
                    <td>                        
                        <form method="POST" action="/DiscographyEcommerce/showartist/${artist.uid}">
                            <input type="submit" value="showdetail" />
                        </form>

                    </td>

                </tr>
            </c:forEach>
        </table>

        
        <p>
            <a href="${pageContext.request.contextPath}/admin/artist/add">Inserisci artista</a>                       
            <a href="${pageContext.request.contextPath}/admin">Admin Management</a>
        </p>
        <br><br>    
        <h2>Welcome : ${pageContext.request.userPrincipal.name} </h2>

        <c:url value="/logout" var="logoutUrl" />
        <a href="${logoutUrl}">Log Out</a>   

